import { Component } from '@angular/core';

@Component({
  selector: 'app-nlp-fundamentals',
  templateUrl: './nlp-fundamentals.component.html',
  styleUrls: ['./nlp-fundamentals.component.css']
})
export class NLPFundamentalsComponent {
  sections = [
    {
      title: "Mathematics in NLP",
      bgColor: "primary",
      topics: [
        { subTitle: "Linear Algebra", description: "Used for word embeddings like Word2Vec, GloVe, and Transformers." },
        { subTitle: "Probability & Statistics", description: "Helps estimate the likelihood of words appearing together (Markov Chains, Hidden Markov Models)." },
        { subTitle: "Optimization & Calculus", description: "Gradient descent and backpropagation are used to train deep learning models." }
      ]
    },
    {
      title: "Statistics in NLP",
      bgColor: "success",
      topics: [
        { subTitle: "N-Gram Models", description: "Predicts word sequences using frequency-based probabilities." },
        { subTitle: "Bayesian Inference", description: "Used in Naïve Bayes for spam filtering and sentiment analysis." },
        { subTitle: "Latent Semantic Analysis", description: "Uses Singular Value Decomposition (SVD) to detect hidden word relationships." }
      ]
    },
    {
      title: "Linguistics in NLP",
      bgColor: "danger",
      topics: [
        { subTitle: "Syntax & Grammar", description: "Analyzes sentence structure using context-free grammars (CFGs)." },
        { subTitle: "Morphology & Semantics", description: "Lemmatization and stemming improve text understanding." },
        { subTitle: "Phonetics & Speech Processing", description: "Essential for speech recognition and text-to-speech synthesis." }
      ]
    },
    {
      title: "History of NLP",
      bgColor: "warning",
      topics: [
        { subTitle: "1950s-60s: Rule-Based NLP", description: "Alan Turing's test and early machine translation systems." },
        { subTitle: "1970s-90s: Statistical NLP", description: "Hidden Markov Models (HMMs) and Statistical Machine Translation (SMT)." },
        { subTitle: "2000s-Present: Deep Learning NLP", description: "Word embeddings, transformers (BERT, GPT), and modern AI chatbots." }
      ]
    },
    {
      title: "Generative AI & NLP",
      bgColor: "info",
      topics: [
        { subTitle: "Language Models", description: "GPT-4, ChatGPT, and other transformer-based models for text generation." },
        { subTitle: "Pretrained Language Models (PLMs)", description: "BERT, T5, and GPT trained on massive datasets for contextual understanding." },
        { subTitle: "Multimodal AI", description: "Combining text, images, and audio for AI-generated content (e.g., DALL·E)." }
      ]
    }
  ];
}
