import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NeuralNetworksAndHumanBrainComponent } from './neural-networks-and-human-brain.component';

describe('NeuralNetworksAndHumanBrainComponent', () => {
  let component: NeuralNetworksAndHumanBrainComponent;
  let fixture: ComponentFixture<NeuralNetworksAndHumanBrainComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NeuralNetworksAndHumanBrainComponent]
    });
    fixture = TestBed.createComponent(NeuralNetworksAndHumanBrainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
