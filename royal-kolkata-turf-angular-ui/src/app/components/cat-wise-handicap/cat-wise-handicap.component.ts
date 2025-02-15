import { Component } from '@angular/core';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';
import { CategoryWiseHandicap } from 'src/app/models/cat-wise-handicap'; 
@Component({
  selector: 'app-cat-wise-handicap',
  templateUrl: './cat-wise-handicap.component.html',
  styleUrls: ['./cat-wise-handicap.component.css']
})
export class CatWiseHandicapComponent {
  catWiseHandicaps?: CategoryWiseHandicap[];
  catWiseHandicap?: CategoryWiseHandicap = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getCatWiseHandicap()
      .subscribe({
        next: (data) => {
          this.catWiseHandicaps = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}



