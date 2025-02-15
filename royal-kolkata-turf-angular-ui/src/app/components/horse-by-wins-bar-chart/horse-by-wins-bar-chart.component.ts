import { AfterViewInit, Component, OnInit } from '@angular/core';
import { HorseByWins } from 'src/app/models/horse-by-wins';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';

import * as Highcharts from 'highcharts';
import HighchartsMore from 'highcharts/highcharts-more';
import HighchartsSolidGauge from 'highcharts/modules/solid-gauge';

HighchartsMore(Highcharts);
HighchartsSolidGauge(Highcharts);
@Component({
  selector: 'app-horse-by-wins-bar-chart',
  templateUrl: './horse-by-wins-bar-chart.component.html',
  styleUrls: ['./horse-by-wins-bar-chart.component.css']
})
export class HorseByWinsBarChartComponent implements OnInit {

  horsesByWins: HorseByWins[] = [];
  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }
    ngOnInit(): void {
  }
  public ngAfterViewInit(): void {
    this.createChartLineForFfm();
  }
  private createChartLineForFfm(): void {
    let date = new Date();
    const data: any[] = [];
    this.royalKolkataTurfService.getHorseByWins()
      .subscribe({
        next: (remoteData) => {
          this.horsesByWins = remoteData;
          console.log(this.horsesByWins);
          for (let j = 0; j < this.horsesByWins.length; j++) {
            data.push([this.horsesByWins[j].horseName, this.horsesByWins[j].wins]);
          }
          console.log('data :' + data);
          console.log('data dynamic :' + this.horsesByWins);
          const chart = Highcharts.chart('horse-by-wins-line-chart', {
            chart: {
              type: 'line',
            },
            title: {
              text: 'Horse By Wins Line Chart',
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
}
