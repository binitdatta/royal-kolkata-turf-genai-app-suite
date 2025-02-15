import { AfterViewInit, Component, OnInit } from '@angular/core';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';
import { TotalRacesPerCategory } from 'src/app/models/total-races-per-category'

import * as Highcharts from 'highcharts';
import HighchartsMore from 'highcharts/highcharts-more';
import HighchartsSolidGauge from 'highcharts/modules/solid-gauge';

HighchartsMore(Highcharts);
HighchartsSolidGauge(Highcharts);

@Component({
  selector: 'app-races-by-category-bar-chart',
  templateUrl: './races-by-category-bar-chart.component.html',
  styleUrls: ['./races-by-category-bar-chart.component.css']
})
export class RacesByCategoryBarChartComponent {
  totalracesPerCategory: TotalRacesPerCategory[] = [];
  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }
  ngOnInit(): void {
  }
  public ngAfterViewInit(): void {
    this.createChartLineForFfm();
    this.createColumnChart();
    this.createPieChart();
    this.createLineChart();
    this.createAreaChart();
    this.createScatterChart();
    this.createGaugeChart1();
    this.createGaugeChart2();
    this.createGaugeChart3();
    this.createGaugeChart4();
  }
  private createChartLineForFfm(): void {
    let date = new Date();
    const data: any[] = [];
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          this.totalracesPerCategory = remoteData;
          console.log(this.totalracesPerCategory);
          for (let j = 0; j < this.totalracesPerCategory.length; j++) {
            data.push([this.totalracesPerCategory[j].category, this.totalracesPerCategory[j].totalRaces]);
          }
          console.log('data :' + data);
          console.log('data dynamic :' + this.totalracesPerCategory);
          const chart = Highcharts.chart('total-races-by-category-bar-chart', {
            chart: {
              type: 'bar',
            },
            title: {
              text: 'Total Races Per Category Bar Chart',
            },
            credits: {
              enabled: false,
            },
            legend: {
              enabled: false,
            },
            yAxis: {
              title: {
                text: null,
              }
            },
            xAxis: {
              type: 'category',
            },
            tooltip: {
              headerFormat: `<div>Date: {point.key}</div>`,
              pointFormat: `<div>{series.name}: {point.y}</div>`,
              shared: true,
              useHTML: true,
            },
            series: [{
              name: 'Amount',
              data,
            }],
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createColumnChart(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          this.totalracesPerCategory = remoteData;
          const data = this.totalracesPerCategory.map(item => [item.category, item.totalRaces]);

          Highcharts.chart('total-races-by-category-column-chart', {
            chart: {
              type: 'column',
            },
            title: {
              text: 'Total Races Per Category Column Chart',
            },
            xAxis: {
              type: 'category',
            },
            yAxis: {
              title: {
                text: 'Total Races',
              }
            },
            series: [{
              name: 'Races',
              data,
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createPieChart(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          this.totalracesPerCategory = remoteData;
          const data = this.totalracesPerCategory.map(item => ({
            name: item.category,
            y: item.totalRaces
          }));

          Highcharts.chart('total-races-by-category-pie-chart', {
            chart: {
              type: 'pie',
            },
            title: {
              text: 'Total Races Per Category Pie Chart',
            },
            tooltip: {
              pointFormat: '{series.name}: <b>{point.y}</b>',
            },
            series: [{
              name: 'Races',
              data,
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createLineChart(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          this.totalracesPerCategory = remoteData;
          const categories = this.totalracesPerCategory.map(item => item.category);
          const seriesData = this.totalracesPerCategory.map(item => item.totalRaces);

          Highcharts.chart('total-races-by-category-line-chart', {
            chart: {
              type: 'line',
            },
            title: {
              text: 'Total Races Per Category Line Chart',
            },
            xAxis: {
              categories,
            },
            yAxis: {
              title: {
                text: 'Total Races',
              }
            },
            series: [{
              name: 'Races',
              data: seriesData,
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createAreaChart(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          this.totalracesPerCategory = remoteData;
          const categories = this.totalracesPerCategory.map(item => item.category);
          const seriesData = this.totalracesPerCategory.map(item => item.totalRaces);

          Highcharts.chart('total-races-by-category-area-chart', {
            chart: {
              type: 'area',
            },
            title: {
              text: 'Total Races Per Category Area Chart',
            },
            xAxis: {
              categories,
            },
            yAxis: {
              title: {
                text: 'Total Races',
              }
            },
            series: [{
              name: 'Races',
              data: seriesData,
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createScatterChart(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          this.totalracesPerCategory = remoteData;

          // Assign numeric index for x-axis
          const data = this.totalracesPerCategory.map((item, index) => ({
            x: index, // ✅ Use numeric index instead of category name
            y: item.totalRaces
          }));

          Highcharts.chart('total-races-by-category-scatter-chart', {
            chart: {
              type: 'scatter',
            },
            title: {
              text: 'Total Races Per Category Scatter Chart',
            },
            xAxis: {
              title: {
                text: 'Category'
              },
              categories: this.totalracesPerCategory.map(item => item.category), // ✅ Map category names for labels
              labels: {
                rotation: -45
              }
            },
            yAxis: {
              title: {
                text: 'Total Races',
              }
            },
            series: [{
              name: 'Races',
              data,
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }


  private createGaugeChart1(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          const category = remoteData.find(item => item.category === 'Thoroughbred');
          const totalRaces = category ? category.totalRaces : 0;

          Highcharts.chart('total-races-gauge-chart', {
            chart: {
              type: 'solidgauge',
            },
            title: {
              text: 'Thoroughbred Races Gauge Chart',
            },
            series: [{
              name: 'Races',
              data: [totalRaces],
              tooltip: {
                valueSuffix: ' races',
              }
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createGaugeChart2(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          const category = remoteData.find(item => item.category === 'Arabian');
          const totalRaces = category ? category.totalRaces : 0;

          Highcharts.chart('total-races-gauge-chart-arabian', {
            chart: {
              type: 'solidgauge',
            },
            title: {
              text: 'Arabian Races Gauge Chart',
            },
            series: [{
              name: 'Races',
              data: [totalRaces],
              tooltip: {
                valueSuffix: ' races',
              }
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createGaugeChart3(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          const category = remoteData.find(item => item.category === 'Warmblood');
          const totalRaces = category ? category.totalRaces : 0;

          Highcharts.chart('total-races-gauge-chart-warmblood', {
            chart: {
              type: 'solidgauge',
            },
            title: {
              text: 'Warmblood Races Gauge Chart',
            },
            series: [{
              name: 'Races',
              data: [totalRaces],
              tooltip: {
                valueSuffix: ' races',
              }
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

  private createGaugeChart4(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (remoteData) => {
          const category = remoteData.find(item => item.category === 'Draft Horse');
          const totalRaces = category ? category.totalRaces : 0;

          Highcharts.chart('total-races-gauge-chart-drafthorse', {
            chart: {
              type: 'solidgauge',
            },
            title: {
              text: 'Draft Horse Races Gauge Chart',
            },
            series: [{
              name: 'Races',
              data: [totalRaces],
              tooltip: {
                valueSuffix: ' races',
              }
            }],
            credits: {
              enabled: false,
            }
          } as any);
        },
        error: (e) => console.error(e)
      });
  }

}

