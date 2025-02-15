import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalRacesPerCategoryComponent } from './total-races-per-category.component';

describe('TotalRacesPerCategoryComponent', () => {
  let component: TotalRacesPerCategoryComponent;
  let fixture: ComponentFixture<TotalRacesPerCategoryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TotalRacesPerCategoryComponent]
    });
    fixture = TestBed.createComponent(TotalRacesPerCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
