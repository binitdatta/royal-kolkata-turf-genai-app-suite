import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RacesByCategoryBarChartComponent } from './races-by-category-bar-chart.component';

describe('RacesByCategoryBarChartComponent', () => {
  let component: RacesByCategoryBarChartComponent;
  let fixture: ComponentFixture<RacesByCategoryBarChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RacesByCategoryBarChartComponent]
    });
    fixture = TestBed.createComponent(RacesByCategoryBarChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
