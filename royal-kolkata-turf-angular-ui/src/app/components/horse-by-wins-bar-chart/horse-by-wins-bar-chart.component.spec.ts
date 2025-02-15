import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HorseByWinsBarChartComponent } from './horse-by-wins-bar-chart.component';

describe('HorseByWinsBarChartComponent', () => {
  let component: HorseByWinsBarChartComponent;
  let fixture: ComponentFixture<HorseByWinsBarChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HorseByWinsBarChartComponent]
    });
    fixture = TestBed.createComponent(HorseByWinsBarChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
