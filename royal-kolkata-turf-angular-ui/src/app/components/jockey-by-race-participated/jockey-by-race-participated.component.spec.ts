import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JockeyByRaceParticipatedComponent } from './jockey-by-race-participated.component';

describe('JockeyByRaceParticipatedComponent', () => {
  let component: JockeyByRaceParticipatedComponent;
  let fixture: ComponentFixture<JockeyByRaceParticipatedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JockeyByRaceParticipatedComponent]
    });
    fixture = TestBed.createComponent(JockeyByRaceParticipatedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
