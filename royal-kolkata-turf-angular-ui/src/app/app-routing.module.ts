import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ArchitectureComponent } from "./architecture/architecture.component";
import { ContactComponent } from "./contact/contact.component";
import { ChatbotComponent } from './chatbot/chatbot.component';
import { TotalRacesPerCategoryComponent } from './components/total-races-per-category/total-races-per-category.component'
import { BetsByRace } from './models/bets-by-race';
import { BetsByRaceComponent } from './components/bets-by-race/bets-by-race.component';
import { CatWiseHandicapComponent } from './components/cat-wise-handicap/cat-wise-handicap.component';
import { JockeyByRaceParticipatedComponent } from './components/jockey-by-race-participated/jockey-by-race-participated.component';
import { HorseByWinsComponent } from './components/horse-by-wins/horse-by-wins.component';
import { MembersByTypeComponent } from './components/members-by-type/members-by-type.component';
import { DynamicSearchComponent } from './components/dynamic-search/dynamic-search.component';
import { FoodDrinksByPopularityComponent } from './components/food-drinks-by-popularity/food-drinks-by-popularity.component';
import { HorseCategoryByWeightComponent } from './components/horse-category-by-weight/horse-category-by-weight.component';
import { MonthlyRevenueByPartyBookinsComponent } from './components/monthly-revenue-by-party-bookins/monthly-revenue-by-party-bookins.component';
import { RevenueByRaceComponent } from './components/revenue-by-race/revenue-by-race.component';
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

const routes: Routes = [
  { path: '', redirectTo: 'activity-stream-mobile', pathMatch: 'full' },
  { path: 'app-horse-grid', component: HorseGridComponent }, // Default path shows the horse grid
  { path: 'app-gen-ai-timeline', component: GenAiTimelineComponent }, // Default path shows the horse grid
  { path: 'app-neural-networks-and-human-brain', component: NeuralNetworksAndHumanBrainComponent }, // Default path shows the horse grid
  { path: 'app-nlp-fundamentals', component: NLPFundamentalsComponent }, // Default path shows the horse grid
  { path: 'app-self-attention', component: SelfAttentionComponent }, // Default path shows the horse grid
  { path: 'app-add-horse', component: AddHorseComponent },
  { path: 'app-edit-horse', component: EditHorseComponent },
  { path: 'app-edit-horse/:id', component: EditHorseComponent }, // ✅ Fix route to match expected path
  { path: 'app-total-races-per-category', component: TotalRacesPerCategoryComponent },
  { path: 'app-bets-by-race', component: BetsByRaceComponent },
  { path: 'app-cat-wise-handicap', component: CatWiseHandicapComponent },
  { path: 'app-jockey-by-race-participated', component: JockeyByRaceParticipatedComponent },
  { path: 'app-horse-by-wins', component: HorseByWinsComponent },
  { path: 'app-members-by-type', component: MembersByTypeComponent },
  { path: 'app-food-drinks-by-popularity', component: FoodDrinksByPopularityComponent },
  { path: 'app-horse-category-by-weight', component: HorseCategoryByWeightComponent },
  { path: 'app-monthly-revenue-by-party-bookins', component: MonthlyRevenueByPartyBookinsComponent },
  { path: 'app-revenue-by-race', component: RevenueByRaceComponent },
  { path: 'app-dynamic-search', component: DynamicSearchComponent },
  { path: 'app-horse-by-wins-bar-chart', component: HorseByWinsBarChartComponent },
  { path: 'app-members-by-type-pie-chart', component: MembersByTypePieChartComponent },
  { path: 'app-races-by-category-bar-chart', component: RacesByCategoryBarChartComponent },
  { path: 'architecture', component: ArchitectureComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'app-chatbot', component: ChatbotComponent },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
