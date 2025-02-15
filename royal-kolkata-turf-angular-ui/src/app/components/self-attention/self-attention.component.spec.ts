import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfAttentionComponent } from './self-attention.component';

describe('SelfAttentionComponent', () => {
  let component: SelfAttentionComponent;
  let fixture: ComponentFixture<SelfAttentionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SelfAttentionComponent]
    });
    fixture = TestBed.createComponent(SelfAttentionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
