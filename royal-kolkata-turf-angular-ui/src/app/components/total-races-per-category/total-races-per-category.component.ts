import { Component } from '@angular/core';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';
import { TotalRacesPerCategory } from 'src/app/models/total-races-per-category'
@Component({
  selector: 'app-total-races-per-category',
  templateUrl: './total-races-per-category.component.html',
  styleUrls: ['./total-races-per-category.component.css']
})
export class TotalRacesPerCategoryComponent {
  totalracesPerCategory?: TotalRacesPerCategory[];
  currentRaceCategory?: TotalRacesPerCategory = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getTotalRacesPerCategory()
      .subscribe({
        next: (data) => {
          this.totalracesPerCategory = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}


