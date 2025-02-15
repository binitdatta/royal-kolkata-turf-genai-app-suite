import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoodDrinksByPopularityComponent } from './food-drinks-by-popularity.component';

describe('FoodDrinksByPopularityComponent', () => {
  let component: FoodDrinksByPopularityComponent;
  let fixture: ComponentFixture<FoodDrinksByPopularityComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FoodDrinksByPopularityComponent]
    });
    fixture = TestBed.createComponent(FoodDrinksByPopularityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
