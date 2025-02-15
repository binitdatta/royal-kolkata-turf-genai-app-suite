import { Component } from '@angular/core';
import { HorseByWins } from 'src/app/models/horse-by-wins';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';

@Component({
  selector: 'app-horse-by-wins',
  templateUrl: './horse-by-wins.component.html',
  styleUrls: ['./horse-by-wins.component.css']
})
export class HorseByWinsComponent {
  horsesByWins?: HorseByWins[];
  horseByWins?: HorseByWins = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getHorseByWins()
      .subscribe({
        next: (data) => {
          this.horsesByWins = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
