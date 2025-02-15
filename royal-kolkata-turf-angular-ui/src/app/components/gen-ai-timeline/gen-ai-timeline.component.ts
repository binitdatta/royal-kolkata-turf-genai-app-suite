import { Component } from '@angular/core';

@Component({
  selector: 'app-gen-ai-timeline',
  templateUrl: './gen-ai-timeline.component.html',
  styleUrls: ['./gen-ai-timeline.component.css']
})
export class GenAiTimelineComponent {
  timeline = [
    {
      year: "1950s-1970s",
      title: "Early AI & Statistical Learning",
      description: "The foundations of AI with rule-based systems, Bayesian inference, and the first neural networks.",
      details: [
        "🔹 1950s: Foundations of Statistical Learning.",
        "🔹 1956: First AI programs (ELIZA, SHRDLU).",
        "🔹 1958: Perceptron by Frank Rosenblatt."
      ]
    },
    {
      year: "1980s",
      title: "Neural Networks & Machine Learning Foundations",
      description: "The resurgence of neural networks with backpropagation and associative memory models.",
      details: [
        "🔹 1982: Hopfield Networks for associative memory.",
        "🔹 1986: Backpropagation algorithm for multi-layer perceptrons."
      ]
    },
    {
      year: "1990s",
      title: "Statistical AI & Deep Learning Precursors",
      description: "Introduction of CNNs and Reinforcement Learning.",
      details: [
        "🔹 1992: Q-Learning and Reinforcement Learning (Sutton & Barto).",
        "🔹 1998: LeNet-5 (First Convolutional Neural Network)."
      ]
    },
    {
      year: "2000s",
      title: "Deep Learning Foundations",
      description: "Breakthroughs in feature learning, convex optimization, and deep networks.",
      details: [
        "🔹 2006: Hinton’s Deep Belief Networks.",
        "🔹 2000s: Support Vector Machines (SVMs) & Kernel Methods."
      ]
    },
    {
      year: "2010s",
      title: "Deep Learning Boom",
      description: "The rise of CNNs, Transformers, GANs, and reinforcement learning in deep AI models.",
      details: [
        "🔹 2013: Variational Autoencoders (VAEs).",
        "🔹 2014: Generative Adversarial Networks (GANs) by Ian Goodfellow.",
        "🔹 2015: Residual Neural Networks (ResNet).",
        "🔹 2017: Transformer Networks ('Attention is All You Need')."
      ]
    },
    {
      year: "2020s-Present",
      title: "Generative AI Revolution",
      description: "Large Language Models, Diffusion Models, and Multimodal AI.",
      details: [
        "🔹 2020-Present: GPT-4, Claude, LLaMA, Gemini, Mistral.",
        "🔹 2020-Present: DALL·E, Stable Diffusion, Imagen.",
        "🔹 2020-Present: Self-Supervised Learning, Reinforcement Learning from Human Feedback (RLHF)."
      ]
    }
  ];
}
