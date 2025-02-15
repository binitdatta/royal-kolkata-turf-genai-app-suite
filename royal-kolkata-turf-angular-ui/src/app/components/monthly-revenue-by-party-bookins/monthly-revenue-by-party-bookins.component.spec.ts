import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyRevenueByPartyBookinsComponent } from './monthly-revenue-by-party-bookins.component';

describe('MonthlyRevenueByPartyBookinsComponent', () => {
  let component: MonthlyRevenueByPartyBookinsComponent;
  let fixture: ComponentFixture<MonthlyRevenueByPartyBookinsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MonthlyRevenueByPartyBookinsComponent]
    });
    fixture = TestBed.createComponent(MonthlyRevenueByPartyBookinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
