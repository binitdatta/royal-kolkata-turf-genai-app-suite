import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RevenueByRaceComponent } from './revenue-by-race.component';

describe('RevenueByRaceComponent', () => {
  let component: RevenueByRaceComponent;
  let fixture: ComponentFixture<RevenueByRaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RevenueByRaceComponent]
    });
    fixture = TestBed.createComponent(RevenueByRaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
