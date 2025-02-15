import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HighchartsChartModule } from 'highcharts-angular';
import { HorseService, Horse } from './services/horse.service';

import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { ArchitectureComponent } from './architecture/architecture.component';
import { ContactComponent } from './contact/contact.component';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { TotalRacesPerCategoryComponent } from './components/total-races-per-category/total-races-per-category.component';
import { BetsByRaceComponent } from './components/bets-by-race/bets-by-race.component';
import { CatWiseHandicapComponent } from './components/cat-wise-handicap/cat-wise-handicap.component';
import { JockeyByRaceParticipatedComponent } from './components/jockey-by-race-participated/jockey-by-race-participated.component';
import { HorseByWinsComponent } from './components/horse-by-wins/horse-by-wins.component';
import { MembersByTypeComponent } from './components/members-by-type/members-by-type.component';
import { DynamicSearchComponent } from './components/dynamic-search/dynamic-search.component';
import { MonthlyRevenueByPartyBookinsComponent } from './components/monthly-revenue-by-party-bookins/monthly-revenue-by-party-bookins.component';
import { HorseCategoryByWeightComponent } from './components/horse-category-by-weight/horse-category-by-weight.component';
import { RevenueByRaceComponent } from './components/revenue-by-race/revenue-by-race.component';
import { FoodDrinksByPopularityComponent } from './components/food-drinks-by-popularity/food-drinks-by-popularity.component';
import { HorseByWinsBarChartComponent } from './components/horse-by-wins-bar-chart/horse-by-wins-bar-chart.component';
import { MembersByTypePieChartComponent } from './components/members-by-type-pie-chart/members-by-type-pie-chart.component';
import { RacesByCategoryBarChartComponent } from './components/races-by-category-bar-chart/races-by-category-bar-chart.component';
import { AddHorseComponent } from './components/add-horse/add-horse.component';
import { EditHorseComponent } from './components/edit-horse/edit-horse.component';
import { HorseGridComponent } from './components/horse-grid/horse-grid.component';
import { GenAiTimelineComponent } from './components/gen-ai-timeline/gen-ai-timeline.component';
import { NeuralNetworksAndHumanBrainComponent } from './components/neural-networks-and-human-brain/neural-networks-and-human-brain.component';
import { NLPFundamentalsComponent } from './components/nlp-fundamentals/nlp-fundamentals.component';
import { SelfAttentionComponent } from './components/self-attention/self-attention.component';

@NgModule({
  declarations: [
    AppComponent,
    ArchitectureComponent,
    ContactComponent,
    ChatbotComponent,
    TotalRacesPerCategoryComponent,
    BetsByRaceComponent,
    CatWiseHandicapComponent,
    JockeyByRaceParticipatedComponent,
    HorseByWinsComponent,
    MembersByTypeComponent,
    DynamicSearchComponent,
    MonthlyRevenueByPartyBookinsComponent,
    HorseCategoryByWeightComponent,
    RevenueByRaceComponent,
    FoodDrinksByPopularityComponent,
    HorseByWinsBarChartComponent,
    MembersByTypePieChartComponent,
    RacesByCategoryBarChartComponent,
    AddHorseComponent,
    EditHorseComponent,
    HorseGridComponent,
    GenAiTimelineComponent,
    NeuralNetworksAndHumanBrainComponent,
    NLPFundamentalsComponent,
    SelfAttentionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HighchartsChartModule
  ],
  providers: [HorseService],
  bootstrap: [AppComponent]
})
export class AppModule { }
