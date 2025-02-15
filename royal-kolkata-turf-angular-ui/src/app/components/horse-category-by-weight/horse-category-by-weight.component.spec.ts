import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HorseCategoryByWeightComponent } from './horse-category-by-weight.component';

describe('HorseCategoryByWeightComponent', () => {
  let component: HorseCategoryByWeightComponent;
  let fixture: ComponentFixture<HorseCategoryByWeightComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HorseCategoryByWeightComponent]
    });
    fixture = TestBed.createComponent(HorseCategoryByWeightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
