import { Component } from '@angular/core';
import { RevenueByRace } from 'src/app/models/revenue-by-race';
import {RoyalKolkataTurfService} from 'src/app/services/rktc-service';
@Component({
  selector: 'app-revenue-by-race',
  templateUrl: './revenue-by-race.component.html',
  styleUrls: ['./revenue-by-race.component.css']
})
export class RevenueByRaceComponent {
  revenueByRaces?: RevenueByRace[];
  revenueByRace?: RevenueByRace = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getRevenueByRace()
      .subscribe({
        next: (data) => {
          this.revenueByRaces = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
