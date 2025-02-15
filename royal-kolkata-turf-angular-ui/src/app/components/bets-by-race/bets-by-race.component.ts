import { Component } from '@angular/core';
import { BetsByRace } from 'src/app/models/bets-by-race';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';
@Component({
  selector: 'app-bets-by-race',
  templateUrl: './bets-by-race.component.html',
  styleUrls: ['./bets-by-race.component.css']
})
export class BetsByRaceComponent {
  betsByRaces?: BetsByRace[];
  currentBetByRace?: BetsByRace = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getBetsByRace()
      .subscribe({
        next: (data) => {
          this.betsByRaces = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}



