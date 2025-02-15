import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenAiTimelineComponent } from './gen-ai-timeline.component';

describe('GenAiTimelineComponent', () => {
  let component: GenAiTimelineComponent;
  let fixture: ComponentFixture<GenAiTimelineComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GenAiTimelineComponent]
    });
    fixture = TestBed.createComponent(GenAiTimelineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
