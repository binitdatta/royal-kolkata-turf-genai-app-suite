# ActivityStreanAngularUiFinal

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.1.3.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI Overview and Command Reference](https://angular.io/cli) page.


import { Component, OnInit } from '@angular/core';

import { ActivityStreamService } from "../services/activitystream-service";

import * as Highcharts from 'highcharts';
import Heatmap from 'highcharts/modules/heatmap';

import { HeatMapModel } from '../models/heatmapmodel';
import { HeatMapElement } from '../models/heatmapelement';
Heatmap(Highcharts);

@Component({
  selector: 'app-heatmap-chart',
  templateUrl: './heatmap-chart.component.html',
  styleUrls: ['./heatmap-chart.component.css']
})
export class HeatmapChartComponent implements OnInit {



  constructor(private activityStreamService: ActivityStreamService) { }
  ngOnInit(): void {
    this.createHM();
  }

  public ngAfterViewInit(): void {
  }

  private createHM(): void {
    console.log("Inside");
    let date = new Date();
    const data: any[] = []; // xAxisDateString
    var xAxisData: any[] = [];
    const yAxisData: any[] = [];
    const heatMapCell: HeatMapElement[] = [];
    // const heatMapCell1: number[] = [];

    var heatMapModel: HeatMapModel;
    this.activityStreamService.getElapsedtimeHeatMapData()
      .subscribe({
        next: (remoteData) => {
          heatMapModel = remoteData;
          // console.log(heatMapModel);
          console.log("remoteData.xAxisDateString.length :" + remoteData.xAxisDateString.length);
          for (let a = 0; a < remoteData.xAxisDateString.length; a++) {
            console.log("KiKhan Modi " + a);
            xAxisData.push(remoteData.xAxisDateString[a]);
          }
          // xAxisData.push(remoteData.xAxisDateString);
          console.log("remoteData.apis.length :" + remoteData.apis.length);

          for (let b = 0; b < remoteData.apis.length; b++) {
            yAxisData.push(remoteData.apis[b]);
          }
          console.log("xAxisData :" + xAxisData);
          console.log("yAxisData :" + yAxisData);
          for (let j = 0; j < heatMapModel?.heatMapCell?.length; j++) {
            let hmElemt: HeatMapElement = {
              row: heatMapModel?.heatMapCell[j].row,
              column: heatMapModel?.heatMapCell[j].column,
              elapsedTime: heatMapModel?.heatMapCell[j].elapsedTime
            }
            var heatMapCell1: number[] = [];
            heatMapCell1.push(hmElemt.row, hmElemt.column, hmElemt.elapsedTime);
            data.push(heatMapCell1);
          }
          console.log(" data :" + JSON.stringify(data))
          const chart = Highcharts.chart('heatmap-chart', {
            chart: {
              type: 'heatmap',
              plotBorderWidth: 1
            },
            title: {
              text: 'Average Elapsed Time Heatmap Chart for APIs',
            },
            xAxis: {
              // categories: xAxisData
              categories: ['2023-07-09', '2023-07-10', '2023-07-11', '2023-07-12', '2023-07-13', '2023-07-14', '2023-07-15', '2023-07-17', '2023-07-18', '2023-07-19']
            },
            yAxis: {
              // categories: yAxisData
              categories: ["fulfillment", "item", "order", "token"]
            },
            colorAxis: {
              min: 0,
              minColor: '#FFFFFF',
              maxColor: "#ADD8E6",
            },
            legend: {
              align: 'right',
              layout: 'vertical',
              margin: 0,
              verticalAlign: 'top',
              y: 25,
              symbolHeight: 280
            },
            series: [{
              type: 'heatmap',
              name: 'Average ElapsedTime per API',
              borderWidth: 1,
              data,
              dataLabels: {
                enabled: true,
                color: '#000000'
              }
            }]
          } as any);
        },
        error: (e) => console.error(e)
      });
  }
}

  @GetMapping("/monthly-revenue-by-party-bookings")
    public ResponseEntity<List<MonthlyRevenyeByPartyBookings>> getMonthlyRevenueByPartyBookings() {
        return ResponseEntity.ok(service.getMonthlyRevenueByPartyBookings());
    }

    @GetMapping("/revenue-by-payouts") //TODO
    public ResponseEntity<List<RevenueByPayouts>> getRevenueByPayouts() {
        return ResponseEntity.ok(service.getRevenueByPayouts());
    }

    @GetMapping("/horse-category-by-weight")
    public ResponseEntity<List<HorseCategoryByWeight>> getHorseCategoryByWeight() {
        return ResponseEntity.ok(service.getHorseCategoryByWeight());
    }

    @GetMapping("/daily-club-revenue-by-tickets-orders") //TODO
    public ResponseEntity<List<DailyClubRevenueByTicketsAndOrder>> getDailyClubRevenueByTicketsAndOrder() {
        return ResponseEntity.ok(service.getDailyClubRevenueByTicketsAndOrder());
    }

    @GetMapping("/betting-outcome-distribution")
    public ResponseEntity<List<BettingOutcome>> getBettingOutcomeDistribution() {
        return ResponseEntity.ok(service.getBettingOutcomeDistribution());
    }

    @GetMapping("/revenue-by-race")
    public ResponseEntity<List<RevenueByRace>> getRevenueByRace() {
        return ResponseEntity.ok(service.getRevenueByRace());
    }

    @GetMapping("/food-and-drinks-popularity")
    public ResponseEntity<List<FoodAndDrinkPopularity>> getFoodAndDrinksPopularity() {
        return ResponseEntity.ok(service.getFoodAndDrinksPopularity());
    }
// /api/analytics/search
    @GetMapping("/search/by-birthdate")
    public ResponseEntity<List<Horse>> findByBirthDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {

        logger.info("Received Birthdate: " + birthDate);
        return ResponseEntity.ok(horseService.findByBirthDate(birthDate));
    }

    // ✅ Search by color
    @GetMapping("/search/by-color")
    public ResponseEntity<List<Horse>> findByColor(@RequestParam String color) {
        return ResponseEntity.ok(horseService.findByColor(color));
    }

    // ✅ Search by gender
    @GetMapping("/search/by-gender")
    public ResponseEntity<List<Horse>> findByGender(@RequestParam Horse.Gender gender) {
        return ResponseEntity.ok(horseService.findByGender(gender));
    }

    // ✅ Search by category ID
    @GetMapping("/search/by-category")
    public ResponseEntity<List<Horse>> findByCategory(@RequestParam Integer categoryId) {
        return ResponseEntity.ok(horseService.findByCategory(categoryId));
    }

    // ✅ Search by handicap rating (greater than or equal to)
    @GetMapping("/search/by-handicap")
    public ResponseEntity<List<Horse>> findByHandicap(@RequestParam BigDecimal handicapRating) {
        return ResponseEntity.ok(horseService.findByHandicapRating(handicapRating));
    }

    // ✅ Search dynamically using multiple filters
    @GetMapping("/search")
    public ResponseEntity<List<Horse>> searchHorses(
            @RequestParam(required = false) String horseName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) BigDecimal handicapRating) {
        logger.info("HorseName : " + horseName);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();  // Start tracking time

        List<Horse> horses = horseService.searchHorses(horseName, birthDate, color, gender, categoryId, handicapRating);

        stopWatch.stop();  // Stop tracking time
        logger.info("Total execution time: {} ms", stopWatch.getTotalTimeMillis());

        return ResponseEntity.ok(horses);
    }

    @GetMapping("/total-horses")
    public ResponseEntity<Integer> getTotalHorses() {
        return ResponseEntity.ok(horseService.getTotalHorses());
    }

    @GetMapping("/horses-by-category")
    public ResponseEntity<List<Map<String, Object>>> getHorsesByCategory() {
        return ResponseEntity.ok(horseService.getHorsesByCategory());
    }

    @GetMapping("/total-races")
    public ResponseEntity<Integer> getTotalRaces() {
        return ResponseEntity.ok(horseService.getTotalRaces());
    }

    @GetMapping("/races-won-by-horse")
    public ResponseEntity<List<Map<String, Object>>> getRacesWonByHorse() {
        return ResponseEntity.ok(horseService.getRacesWonByHorse());
    }

    @GetMapping("/total-jockeys")
    public ResponseEntity<Integer> getTotalJockeys() {
        return ResponseEntity.ok(horseService.getTotalJockeys());
    }

    @GetMapping("/jockey-race-count")
    public ResponseEntity<List<Map<String, Object>>> getJockeyRaceCount() {
        return ResponseEntity.ok(horseService.getJockeyRaceCount());
    }

    @GetMapping("/fastest-race-time")
    public ResponseEntity<Map<String, Object>> getFastestRaceTime() {
        return ResponseEntity.ok(horseService.getFastestRaceTime());
    }

    @GetMapping("/most-expensive-party-booking")
    public ResponseEntity<Map<String, Object>> getMostExpensivePartyBooking() {
        return ResponseEntity.ok(horseService.getMostExpensivePartyBooking());
    }



    @GetMapping("/food-and-drink-count")
    public ResponseEntity<List<Map<String, Object>>> getFoodAndDrinksCount() {
        return ResponseEntity.ok(horseService.getFoodAndDrinksCount());
    }

    @GetMapping("/total-club-members")
    public ResponseEntity<Integer> getTotalClubMembers() {
        return ResponseEntity.ok(horseService.getTotalClubMembers());
    }