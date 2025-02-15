import { Component } from '@angular/core';

@Component({
  selector: 'app-self-attention',
  templateUrl: './self-attention.component.html',
  styleUrls: ['./self-attention.component.css']
})
export class SelfAttentionComponent {
  sections = [
    {
      title: "What is Self-Attention?",
      bgColor: "primary",
      description: "Self-attention is a mechanism that allows a model to weigh different words (tokens) in a sentence based on their relationships, regardless of their distance. It is key to models like BERT, GPT-4, and T5."
    },
    {
      title: "How Self-Attention Works",
      bgColor: "success",
      description: "Each word in a sentence is converted into Query, Key, and Value matrices. A dot-product similarity computes attention scores, which weight the words dynamically for better context understanding."
    },
    {
      title: "When Was Self-Attention Invented?",
      bgColor: "danger",
      description: "Self-attention was introduced in the Transformer model in June 2017 in the paper 'Attention Is All You Need', presented at NeurIPS 2017."
    },
    {
      title: "Which Organization Developed It?",
      bgColor: "warning",
      description: "The Transformer model, which introduced self-attention, was developed by Google Brain, an AI research division of Google Research."
    },
    {
      title: "Who Were the People Behind It?",
      bgColor: "info",
      description: "The paper was authored by Ashish Vaswani, Noam Shazeer, Niki Parmar, Jakob Uszkoreit, Llion Jones, Aidan N. Gomez, Łukasz Kaiser, and Illia Polosukhin."
    },
    {
      title: "Why Is Self-Attention Important?",
      bgColor: "dark",
      description: "Self-attention enables efficient long-sequence handling, better word relationship modeling, and faster training. It powers Generative AI models like ChatGPT, BERT, and DALL·E."
    },
    {
      title: "Real-Life Example: ChatGPT Understanding a User Question",
      bgColor: "primary",
      description: "If you ask: 'Can you tell me who wrote the Transformer paper and why it’s important?', self-attention ensures that words like 'who' and 'wrote' are linked to focus on people, and 'Transformer paper' is connected to 'important' to generate an accurate response."
    }
  ];
}
