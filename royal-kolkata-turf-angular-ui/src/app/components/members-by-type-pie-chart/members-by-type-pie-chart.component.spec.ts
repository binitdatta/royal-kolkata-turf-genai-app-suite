import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersByTypePieChartComponent } from './members-by-type-pie-chart.component';

describe('MembersByTypePieChartComponent', () => {
  let component: MembersByTypePieChartComponent;
  let fixture: ComponentFixture<MembersByTypePieChartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MembersByTypePieChartComponent]
    });
    fixture = TestBed.createComponent(MembersByTypePieChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
