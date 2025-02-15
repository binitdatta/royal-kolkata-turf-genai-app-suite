import { Component } from '@angular/core';
import { MonthlyRevenueByPartyBooking } from 'src/app/models/monthly-revenue-by-party-bookins';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';
@Component({
  selector: 'app-monthly-revenue-by-party-bookins',
  templateUrl: './monthly-revenue-by-party-bookins.component.html',
  styleUrls: ['./monthly-revenue-by-party-bookins.component.css']
})
export class MonthlyRevenueByPartyBookinsComponent {
  monthlyRevenueByPartiBookings?: MonthlyRevenueByPartyBooking[];
  monthlyRevenueByPartiBooking?: MonthlyRevenueByPartyBooking = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getMonthlyRevenueByPartyBookings()
      .subscribe({
        next: (data) => {
          this.monthlyRevenueByPartiBookings = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
