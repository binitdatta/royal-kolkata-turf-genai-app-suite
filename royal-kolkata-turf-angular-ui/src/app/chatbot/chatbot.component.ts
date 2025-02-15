import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // Import CommonModule for *ngFor
import * as Highcharts from 'highcharts';
import { Renderer2, ElementRef, ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {
  @Output() close = new EventEmitter<void>(); // Emit an event when the chatbot is closed
  userInput = ''; // Stores the user's input
  #messages: { text: string; isUser: boolean }[] = []; // Stores chat messages
  messages: { text: string; isUser: boolean; isHistogram?: boolean }[] = []; // Stores chat messages
  isHistogram = false;
  isTable = false;
  apiUrl = 'http://localhost:8993/turf/analytics'; // Backend API URL
  isWaitingForResponse = false; // Indicates if the chatbot is waiting for a response

  Highcharts: typeof Highcharts = Highcharts; // Highcharts instance
  chartOptions: Highcharts.Options = {}; // Initialized with an empty object

  constructor(private http: HttpClient, private changeDetector: ChangeDetectorRef) { }

  // Method to close the chatbot
  closeChatbot() {
    this.close.emit();
  }

  sendMessage() {
    if (!this.userInput.trim()) {
      return; // Prevent empty messages
    }

    const userMessage = this.userInput;
    this.messages.push({ text: userMessage, isUser: true });
    this.userInput = '';

    // Show "thinking..." message
    this.isWaitingForResponse = true;
    this.isTable = false;
    this.messages.push({ text: 'Chatbot is thinking...', isUser: false });

    this.http.post<{ answer: string }>(this.apiUrl, { question: userMessage }).subscribe(
      (response) => {
        const rawAnswer = response.answer;
        console.log('Raw answer:', rawAnswer);
        // Extract JSON from response
        //const jsonMatch = rawAnswer.match(/```json\s*(.*?)```/s);
        // const jsonMatch = rawAnswer.match(/```(?:json\s*)?(.*?)```/s);
        var jsonMatch = rawAnswer.match(/```json\s*{[^}]+}\s*(\[[\s\S]*?\])```/s);
        jsonMatch = rawAnswer.match(/```(?:json\s*)?(.*?)```/s);

        let extractedJson: string | null = null;

        if (jsonMatch && jsonMatch[1]) {
          extractedJson = jsonMatch[1].trim(); // Remove extra spaces
        } else {
          // If no triple backticks found, assume the response is already JSON
          try {
            JSON.parse(rawAnswer); // Validate if rawAnswer is already JSON
            extractedJson = rawAnswer;
          } catch (error) {
            console.error("No valid JSON found in response.");
          }
        }

        //If we have extracted JSON, parse it
        if (extractedJson) {
          try {
            const parsedData = JSON.parse(extractedJson);
            console.log("Extracted JSON:", parsedData);

            // Now check if it's horse data
            if (parsedData.horses && Array.isArray(parsedData.horses)) {
              console.log("Horse Data Detected:", parsedData.horses);
              this.isTable = true;
              this.renderHorseTable(parsedData.horses);
            }
          } catch (error) {
            console.error("Failed to parse JSON:", error);
          }
        }
        console.log("jsonMatch", jsonMatch)
        if (jsonMatch && jsonMatch[1]) {
          try {
            const cleanedJson = jsonMatch[1].trim();
            const parsedData = JSON.parse(cleanedJson);

            console.log("Extracted JSON:", parsedData);

            // Handle wrapped JSON in "data" field
            const extractedData = parsedData.data ? parsedData.data : parsedData;
            console.log("Extracted JSON:", extractedData);
            // Determine Chart Type
            if (Array.isArray(extractedData)) {
              if (extractedData.length > 0 && "timeBin" in extractedData[0] && "count" in extractedData[0]) {
                this.renderHistogram(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the histogram for your query:',
                  isUser: false,
                  isHistogram: true,
                };
              } else if (extractedData.length > 0 && "category" in extractedData[0] && "totalRaces" in extractedData[0]) {
                this.renderBarChartHorsesByCategory(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the bar chart for your query:',
                  isUser: false,
                  isHistogram: true, // Reuse flag for charts
                };
              } else if (extractedData.length > 0 && "race" in extractedData[0] && "totalBets" in extractedData[0]) {
                console.log("Total Bets By Race", extractedData)
                this.renderBarChartBetsByRace(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the bar chart for your query:',
                  isUser: false,
                  isHistogram: true, // Reuse flag for charts
                };
              } else if (extractedData.length > 0 && "category" in extractedData[0] && "averageHandiCapRating" in extractedData[0]) {
                console.log("Cat Wise Avt. Handicap", extractedData)
                this.renderBarChartCategoryWiseAverageHandicap(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the bar chart for your query:',
                  isUser: false,
                  isHistogram: true, // Reuse flag for charts
                };
              } else if (extractedData.length > 0 && "jockey" in extractedData[0] && "raceParticipated" in extractedData[0]) {
                console.log("Jockey Wise Race Participation", extractedData)
                this.renderBarChartRaceWiseJockeyParticipation(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the bar chart for your query:',
                  isUser: false,
                  isHistogram: true, // Reuse flag for charts
                };
              } else if (extractedData.length > 0 && "horseName" in extractedData[0] && "wins" in extractedData[0]) {
                console.log("Horses By Wins", extractedData)
                this.renderBarChartHorsebyWins(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the bar chart for your query:',
                  isUser: false,
                  isHistogram: true, // Reuse flag for charts
                };
              } else if (extractedData.length > 0 && "membershipType" in extractedData[0] && "totalMembers" in extractedData[0]) {
                console.log("Horses By Wins", extractedData)
                this.renderPieChartMembershipDistribution(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the bar chart for your query:',
                  isUser: false,
                  isHistogram: true, // Reuse flag for charts
                };
              } else if (extractedData.length > 0 && "month" in extractedData[0] && "totalRevenue" in extractedData[0]) {
                console.log("Monthly Revenue By Party Bookings", extractedData)
                this.renderLineChartRevenue(extractedData);
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the bar chart for your query:',
                  isUser: false,
                  isHistogram: true, // Reuse flag for charts
                };
              } else if (parsedData.horses) { // && Array.isArray(parsedData.horses)) {
                // else if ("horseId" in parsedData[0] && "name" in parsedData[0]) {
                // ✅ Detect Horse Data and Render Table
                //if (parsedData.horses && Array.isArray(parsedData.horses)) {
                  console.log("LG!!! Horse Data Detected:", parsedData.horses);
                  this.renderHorseTable(parsedData.horses);
                //}
                this.messages[this.messages.length - 1] = {
                  text: 'Here is the table for your query:',
                  isUser: false,
                  //isTable: true
                };
              } else if (this.isTable == false){
                this.messages[this.messages.length - 1] = { text: rawAnswer, isUser: false };
              }
            } else if (this.isTable == false) {
              this.messages[this.messages.length - 1] = { text: rawAnswer, isUser: false };
            }
          } catch (error) {
            console.error('Failed to parse JSON:', error);
            this.messages[this.messages.length - 1] = { text: rawAnswer, isUser: false };
          }
        } else {
          if (this.isTable == false) {
            this.messages[this.messages.length - 1] = { text: rawAnswer, isUser: false };
          }
        }

        this.isWaitingForResponse = false;
      },
      (error) => {
        console.error('Error from API:', error);
        this.messages[this.messages.length - 1] = { text: 'An error occurred. Please try again.', isUser: false };
        this.isWaitingForResponse = false;
      }
    );
  }


  private renderHistogram(histogramData: any[]) {
    const categories = histogramData.map((item) => `${item.timeBin}-${item.timeBin + 100}`);
    const seriesData = histogramData.map((item) => item.count);

    this.chartOptions = {
      chart: {
        type: 'column',
      },
      title: {
        text: 'Histogram of Elapsed Time',
      },
      xAxis: {
        categories,
        title: {
          text: 'Time Bins (ms)',
        },
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Count',
        },
      },
      series: [
        {
          name: 'Elapsed Time',
          type: 'column',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `Time Bin: <b>${this.x}</b><br>Count: <b>${this.y}</b>`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  private renderBarChartHorsesByCategory(barChartData: any[]) {
    const categories = barChartData.map((item) => item.category);
    const seriesData = barChartData.map((item) => item.totalRaces);

    this.chartOptions = {
      chart: {
        type: 'bar',
      },
      title: {
        text: 'Total Races by Horse Category',
      },
      xAxis: {
        categories,
        title: {
          text: 'Horse Categories',
        },
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Total Races',
        },
      },
      series: [
        {
          name: 'Total Races',
          type: 'bar',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `Category: <b>${this.x}</b><br>Total Races: <b>${this.y}</b>`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  private renderBarChartBetsByRace(barChartData: any[]) {
    const categories = barChartData.map((item) => item.race);
    const seriesData = barChartData.map((item) => item.totalBets);
    this.chartOptions = {
      chart: {
        type: 'bar',
      },
      title: {
        text: 'Total Bets by Race',
      },
      xAxis: {
        categories,
        title: {
          text: 'Race',
        },
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Total Bets',
        },
      },
      series: [
        {
          name: 'Total Races',
          type: 'bar',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `Bets: <b>${this.x}</b><br>Total Bets: <b>${this.y}</b>`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  private renderBarChartCategoryWiseAverageHandicap(barChartData: any[]) {
    const categories = barChartData.map((item) => item.category);
    const seriesData = barChartData.map((item) => item.averageHandiCapRating);
    this.chartOptions = {
      chart: {
        type: 'bar',
      },
      title: {
        text: 'Horse Category Wise Average Handicap Rating',
      },
      xAxis: {
        categories,
        title: {
          text: 'Category',
        },
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Avg. Handicap',
        },
      },
      series: [
        {
          name: 'Avg. Handicap',
          type: 'bar',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `Bets: <b>${this.x}</b><br>Total Bets: <b>${this.y}</b>`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  private renderBarChartRaceWiseJockeyParticipation(barChartData: any[]) {
    const categories = barChartData.map((item) => item.jockey);
    const seriesData = barChartData.map((item) => item.raceParticipated);
    this.chartOptions = {
      chart: {
        type: 'bar',
      },
      title: {
        text: 'Jockey Wise Race Participation',
      },
      xAxis: {
        categories,
        title: {
          text: 'Race Participated',
        },
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Jockey',
        },
      },
      series: [
        {
          name: 'Jockey Wise Race Participation',
          type: 'bar',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `Bets: <b>${this.x}</b><br>Total Bets: <b>${this.y}</b>`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  private renderBarChartHorsebyWins(barChartData: any[]) {
    const categories = barChartData.map((item) => item.horseName);
    const seriesData = barChartData.map((item) => item.wins);
    this.chartOptions = {
      chart: {
        type: 'bar',
      },
      title: {
        text: 'Horse Wise Wins',
      },
      xAxis: {
        categories,
        title: {
          text: 'Race Won',
        },
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Jockey',
        },
      },
      series: [
        {
          name: 'Horse Wise Wins',
          type: 'bar',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `Bets: <b>${this.x}</b><br>Total Bets: <b>${this.y}</b>`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  private renderPieChartMembershipDistribution(pieChartData: any[]) {
    const seriesData = pieChartData.map((item) => ({
      name: item.membershipType,
      y: item.totalMembers
    }));

    this.chartOptions = {
      chart: {
        type: 'pie',
      },
      title: {
        text: 'Membership Distribution',
      },
      tooltip: {
        formatter: function () {
          return `<b>${this.point.name}</b>: ${this.point.y} members`;
        },
      },
      plotOptions: {
        pie: {
          allowPointSelect: true,
          cursor: 'pointer',
          dataLabels: {
            enabled: true,
            format: '<b>{point.name}</b>: {point.y} members'
          }
        }
      },
      series: [
        {
          name: 'Members',
          type: 'pie',
          data: seriesData,
        },
      ],
      credits: {
        enabled: false,
      },
    };
  }

  private renderLineChartRevenue(monthlyRevenueData: any[]) {
    const categories = monthlyRevenueData.map(item => `${item.month}`);
    const seriesData = monthlyRevenueData.map(item => item.totalRevenue);

    this.chartOptions = {
      chart: {
        type: 'line',
      },
      title: {
        text: 'Monthly Revenue for 2025',
      },
      xAxis: {
        categories,
        title: {
          text: 'Month',
        },
      },
      yAxis: {
        title: {
          text: 'Total Revenue ($)',
        },
      },
      series: [
        {
          name: 'Revenue',
          type: 'line',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `<b>${this.x}</b>: $${this.y}`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  private renderColumnChartRevenue(monthlyRevenueData: any[]) {
    const categories = monthlyRevenueData.map(item => `Month ${item.month}`);
    const seriesData = monthlyRevenueData.map(item => item.totalRevenue);

    this.chartOptions = {
      chart: {
        type: 'column',
      },
      title: {
        text: 'Monthly Revenue for 2025',
      },
      xAxis: {
        categories,
        title: {
          text: 'Month',
        },
      },
      yAxis: {
        title: {
          text: 'Total Revenue ($)',
        },
      },
      series: [
        {
          name: 'Revenue',
          type: 'column',
          data: seriesData,
        },
      ],
      tooltip: {
        formatter: function () {
          return `<b>${this.x}</b>: $${this.y}`;
        },
      },
      credits: {
        enabled: false,
      },
    };
  }

  refreshChatbot() {
    console.log("Refreshing Chatbot...");

    // Clear messages
    this.messages = [];

    // Clear user input
    this.userInput = '';

    // Reset chart options
    this.chartOptions = {};

    // Reset table display flag
    this.isTable = false;

    // Clear table container
    let tableContainer = document.getElementById('dynamicTableContainer');
    if (tableContainer) {
      tableContainer.innerHTML = ''; // Remove any existing table content
    }

    // Notify Angular to update the UI
    this.changeDetector.detectChanges();
  }
  
  private renderHorseTable(horseData: any[]) {
    console.log("Rendering Horse Table...");

    let tableHTML = `
    <div class="table-responsive">
      <table class="table table-striped table-bordered">
        <thead class="thead-dark">
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Birth Date</th>
            <th>Gender</th>
            <th>Color</th>
            <th>Height (hh)</th>
            <th>Weight (lbs)</th>
            <th>Birthmarks</th>
            <th>Category</th>
            <th>Handicap Rating</th>
          </tr>
        </thead>
        <tbody>`;

    horseData.forEach(horse => {
      tableHTML += `
      <tr>
        <td>${horse.horseId}</td>
        <td>${horse.name}</td>
        <td>${new Date(horse.birthDate).toISOString().split('T')[0]}</td>
        <td>${horse.gender}</td>
        <td>${horse.color}</td>
        <td>${horse.height}</td>
        <td>${horse.weight}</td>
        <td>${horse.birthmarks}</td>
        <td>${horse.category.categoryName}</td>
        <td>${horse.handicapRating}</td>
      </tr>`;
    });

    tableHTML += `</tbody></table></div>`;

    // Ensure the chatbot body has a placeholder div
    let tableContainer = document.getElementById('dynamicTableContainer');
    if (tableContainer) {
      tableContainer.innerHTML = tableHTML;
      console.log("Table rendered successfully!");
    } else {
      console.error("Table container not found!");
    }

    this.changeDetector.detectChanges(); // Force UI update
  }

  setPredefinedMessage(message: string) {
    this.userInput = message; // Populate the textarea with the selected message
  }
}
