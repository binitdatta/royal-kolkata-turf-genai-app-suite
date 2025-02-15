import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HorseByWinsComponent } from './horse-by-wins.component';

describe('HorseByWinsComponent', () => {
  let component: HorseByWinsComponent;
  let fixture: ComponentFixture<HorseByWinsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HorseByWinsComponent]
    });
    fixture = TestBed.createComponent(HorseByWinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
