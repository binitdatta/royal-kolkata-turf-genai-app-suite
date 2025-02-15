import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NlpFundamentalsComponent } from './nlp-fundamentals.component';

describe('NlpFundamentalsComponent', () => {
  let component: NlpFundamentalsComponent;
  let fixture: ComponentFixture<NlpFundamentalsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NlpFundamentalsComponent]
    });
    fixture = TestBed.createComponent(NlpFundamentalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
