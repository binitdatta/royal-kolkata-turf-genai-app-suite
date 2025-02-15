import { Component } from '@angular/core';
import { HorseCategoryByWeight } from 'src/app/models/horse-category-by-weight';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';
@Component({
  selector: 'app-horse-category-by-weight',
  templateUrl: './horse-category-by-weight.component.html',
  styleUrls: ['./horse-category-by-weight.component.css']
})
export class HorseCategoryByWeightComponent {
  horsesCategoryByWeights?: HorseCategoryByWeight[];
  horsesCategoryByWeight?: HorseCategoryByWeight = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getHorseCategoryByWeight()
      .subscribe({
        next: (data) => {
          this.horsesCategoryByWeights = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}

