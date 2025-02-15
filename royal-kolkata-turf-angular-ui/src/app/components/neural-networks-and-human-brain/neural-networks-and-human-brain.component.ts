import { Component } from '@angular/core';

@Component({
  selector: 'app-neural-networks-and-human-brain',
  templateUrl: './neural-networks-and-human-brain.component.html',
  styleUrls: ['./neural-networks-and-human-brain.component.css']
})
export class NeuralNetworksAndHumanBrainComponent {

  similarities = [
    { title: "Neuron-like Units", description: "Both use units that receive inputs, process them, and generate outputs." },
    { title: "Connections and Layers", description: "The brain has layers of neurons, just like neural networks have input, hidden, and output layers." },
    { title: "Learning and Adaptation", description: "The brain strengthens connections based on experience; ANNs adjust weights via backpropagation." },
    { title: "Parallel Processing", description: "Both process multiple pieces of information at the same time." },
    { title: "Pattern Recognition", description: "Both recognize patterns like images and speech." }
  ];

  differences = [
    { title: "Energy Efficiency", description: "The brain uses only ~20W, while ANNs require high computational power." },
    { title: "Learning Mechanism", description: "The brain learns continuously; ANNs need retraining." },
    { title: "Memory Storage", description: "The brain stores memories flexibly, while ANNs need large datasets." },
    { title: "Generalization", description: "Humans generalize from few examples; ANNs struggle without large training sets." },
    { title: "Chemical Processing", description: "The brain uses neurotransmitters; ANNs rely purely on math." },
    { title: "Robustness", description: "The brain heals from damage, but ANNs can fail with minor errors." }
  ];
}

