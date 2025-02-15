import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatWiseHandicapComponent } from './cat-wise-handicap.component';

describe('CatWiseHandicapComponent', () => {
  let component: CatWiseHandicapComponent;
  let fixture: ComponentFixture<CatWiseHandicapComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CatWiseHandicapComponent]
    });
    fixture = TestBed.createComponent(CatWiseHandicapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
