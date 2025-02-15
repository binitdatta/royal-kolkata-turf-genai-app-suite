import { Component } from '@angular/core';
import { FoodAndDrinksByPopularity } from 'src/app/models/food-and-drinks-by-popularity';
import { RoyalKolkataTurfService } from 'src/app/services/rktc-service';

@Component({
  selector: 'app-food-drinks-by-popularity',
  templateUrl: './food-drinks-by-popularity.component.html',
  styleUrls: ['./food-drinks-by-popularity.component.css']
})
export class FoodDrinksByPopularityComponent {
  foodAndDrinks?: FoodAndDrinksByPopularity[];
  foodAndDrink?: FoodAndDrinksByPopularity = {};
  currentIndex = -1;
  title = '';

  constructor(private royalKolkataTurfService: RoyalKolkataTurfService) { }

  ngOnInit(): void {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.royalKolkataTurfService.getFoodAndDrinksByPopularity()
      .subscribe({
        next: (data) => {
          this.foodAndDrinks = data;
          console.log(data);
        },
        error: (e) => console.error(e)
      });
  }
}
