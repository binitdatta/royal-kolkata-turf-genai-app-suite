import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MembersByTypeComponent } from './members-by-type.component';

describe('MembersByTypeComponent', () => {
  let component: MembersByTypeComponent;
  let fixture: ComponentFixture<MembersByTypeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MembersByTypeComponent]
    });
    fixture = TestBed.createComponent(MembersByTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
