import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BetsByRaceComponent } from './bets-by-race.component';

describe('BetsByRaceComponent', () => {
  let component: BetsByRaceComponent;
  let fixture: ComponentFixture<BetsByRaceComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BetsByRaceComponent]
    });
    fixture = TestBed.createComponent(BetsByRaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
