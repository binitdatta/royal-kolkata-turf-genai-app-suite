import { Component } from '@angular/core';
import { MembersByType } from 'src/app/models/members-by-type';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';
import * as Highcharts from 'highcharts';
import More from 'highcharts/highcharts-more';
More(Highcharts);
import Drilldown from 'highcharts/modules/drilldown';
Drilldown(Highcharts);

@Component({
  selector: 'app-members-by-type-pie-chart',
  templateUrl: './members-by-type-pie-chart.component.html',
  styleUrls: ['./members-by-type-pie-chart.component.css']
})
export class MembersByTypePieChartComponent {


  membersByType: MembersByType[] = [];

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
  }

  public ngAfterViewInit(): void {
    this.createPieChartForMembersByType();
  }

  private createPieChartForMembersByType(): void {
    let date = new Date();
    const data: any[] = [];
    this.royalKolkataTurfService.getMembersByType()
      .subscribe({
        next: (remoteData) => {
          this.membersByType = remoteData;
          console.log(this.membersByType);
          for (let j = 0; j < this.membersByType.length; j++) {
            data.push([this.membersByType[j].membershipType, this.membersByType[j].totalMembers]);
          }
          console.log('data :' + data);
          console.log('data dynamic :' + this.membersByType);
          const chart = Highcharts.chart('members-by-type-pie-chart' as any, {
            chart: {
              type: 'pie'
            },
            title: {
              text: 'Members By Type Pie Chart',
            },
            tooltip: {
              pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
              pie: {
                allowPointSelect: true,
                showInLegend: true,
                slicedOffset: 10,
                cursor: 'pointer',
                dataLabels: {
                  enabled: true,
                  format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
              }
            },
            series: [{
              type: "pie",
              name: 'Uses',
              data,
            }],
          } as any);
        },
        error: (e: any) => console.error(e)
      });
  }

}


