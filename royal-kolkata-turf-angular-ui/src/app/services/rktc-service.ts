import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { TotalRacesPerCategory } from '../models/total-races-per-category';
import { BetsByRace } from '../models/bets-by-race';
import { CategoryWiseHandicap } from '../models/cat-wise-handicap';
import { JockeyByRaceParticipated } from '../models/jockey-by-rape-participated';
import { HorseByWins } from '../models/horse-by-wins';
import { MembersByType } from '../models/members-by-type';
import { HorseResult } from '../models/horse-results';
import { MonthlyRevenueByPartyBooking } from '../models/monthly-revenue-by-party-bookins';
import { HorseCategoryByWeight } from '../models/horse-category-by-weight';
import { RevenueByRace } from '../models/revenue-by-race';
import { FoodAndDrinksByPopularity } from '../models/food-and-drinks-by-popularity';

const baseUrlRoyalKolkataTotalRacePerCategory = 'http://localhost:8079/api/analytics/total-races-per-category';

const baseUrlBetsByRace = 'http://localhost:8079/api/analytics/bets-by-race';

const baseUrlCatWiseHandicap = 'http://localhost:8079/api/analytics/cat-wise-handicap';
const baseUrlJockeyByRaceParticipated = 'http://localhost:8079/api/analytics/jockey-by-race-participated';
const baseUrlJHorseByWins = 'http://localhost:8079/api/analytics/horse-by-wins';
const baseUrlMembershipByType = 'http://localhost:8079/api/analytics/membership-by-type';
const baseUrlHorseSearch = 'http://localhost:8079/api/analytics/search';
const baseUrlMonthlyRevenueByPartyBooking = 'http://localhost:8079/api/analytics/monthly-revenue-by-party-bookings';
const baseUrlHorseCategoryByWeight = 'http://localhost:8079/api/analytics/horse-category-by-weight';
const baseUrlRevenueByRace = 'http://localhost:8079/api/analytics/revenue-by-race';
const baseUrlFoodAndDrinksByPopularity = 'http://localhost:8079/api/analytics/food-and-drinks-popularity';


@Injectable({
  providedIn: 'root'
})
export class RoyalKolkataTurfService {

  constructor(private http: HttpClient) { }



  getTotalRacesPerCategory(): Observable<TotalRacesPerCategory[]> {
    return this.http.get<TotalRacesPerCategory[]>(baseUrlRoyalKolkataTotalRacePerCategory);
  }

  getBetsByRace(): Observable<BetsByRace[]> {
    return this.http.get<BetsByRace[]>(baseUrlBetsByRace);
  }

  getCatWiseHandicap(): Observable<CategoryWiseHandicap[]> {
    return this.http.get<CategoryWiseHandicap[]>(baseUrlCatWiseHandicap);
  }

  getJockeysByRaceParticipated(): Observable<JockeyByRaceParticipated[]> {
    return this.http.get<JockeyByRaceParticipated[]>(baseUrlJockeyByRaceParticipated);
  }

  getHorseByWins(): Observable<HorseByWins[]> {
    return this.http.get<HorseByWins[]>(baseUrlJHorseByWins);
  }

  getMembersByType(): Observable<MembersByType[]> {
    return this.http.get<MembersByType[]>(baseUrlMembershipByType);
  }

  getMonthlyRevenueByPartyBookings(): Observable<MonthlyRevenueByPartyBooking[]> {
    return this.http.get<MonthlyRevenueByPartyBooking[]>(baseUrlMonthlyRevenueByPartyBooking);
  }

  getHorseCategoryByWeight(): Observable<HorseCategoryByWeight[]> {
    return this.http.get<HorseCategoryByWeight[]>(baseUrlHorseCategoryByWeight);
  }

  getRevenueByRace(): Observable<RevenueByRace[]> {
    return this.http.get<RevenueByRace[]>(baseUrlRevenueByRace);
  }

  // 


  getFoodAndDrinksByPopularity(): Observable<FoodAndDrinksByPopularity[]> {
    return this.http.get<FoodAndDrinksByPopularity[]>(baseUrlFoodAndDrinksByPopularity);
  }

  getHorseSearchResults(): Observable<HorseResult[]> {
    return this.http.get<HorseResult[]>(baseUrlHorseSearch);
  }

}
