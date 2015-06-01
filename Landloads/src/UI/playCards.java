package UI;

import java.util.ArrayList;


public class playCards
{
	//ArrayList<Card> cards = new ArrayList<Card>();		//当前选中的牌
	Card[] cards;
	int cardCount;										//当前选中的牌数
	int type;											//当前选中牌的类型
	int beginNum;										//当前合法的牌的最小值
	//ArrayList<Card> lastCards = new ArrayList<Card>();	//当前最大的牌
	Card[] lastCards;
	int lastCardCount;									//当前最大的牌数
	int lastType;										//当前最大的牌的类型
	int lastBeginNum;									//当前最大的牌的最小值
	boolean playCard;									//这个牌可不可以出
	boolean legalCard;									//这个牌型是不是合法
	
	//client p;
	
	playCards(Card[] cards, int count, Card[] lastCards, int lastCardCount)
	{
		//this.p = p;
		this.cards = cards;
		this.cardCount = count;
		this.lastCards = lastCards;
		this.lastCardCount = lastCardCount;
		this.lastType = 0;
		this.lastBeginNum = 0;
		this.legalCard = false;
		this.playCard = false;
	}
/*	
	void addCard(Card card)
	{
		this.cardCount++;
		this.cards.add(card);
	}
	
	void removeCard(Card card)
	{
		this.cardCount--;
		this.cards.remove(card);
	}
*/	
	void judgeType()
	{
		Card[] tempCard = cards;
		for(int j = 0; j < cardCount -1 ; j++){
			for(int k = 0; k < cardCount - j - 1; k++){
				if(tempCard[k].getNumOfCard() < tempCard[k+1].getNumOfCard()){
					int color;
					int num1;
					color = tempCard[k].color;
					num1 = tempCard[k].num;
					tempCard[k].color = tempCard[k+1].color;
					tempCard[k].num = tempCard[k+1].num;
					tempCard[k+1].color = color;
					tempCard[k+1].num = num1;
				}
			}
		}
		
		type = 0;
		legalCard = false;
		switch (cardCount) {
		case 0:
			type = 0;
			legalCard = false;
			break;
		case 1:
			type = 1;
			legalCard = true;
			beginNum = tempCard[0].getNumOfCard();
			break;
		case 2:
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard()) 
			{
				type = 2;
				legalCard = true;
				beginNum = tempCard[1].getNumOfCard();
				break;
			}
			else if (tempCard[0].color == 5 && tempCard[1].color == 5)
			{
				type = 10;
				legalCard = true;
				break;
			}
			legalCard = false;
			break;
		case 3:
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[1].getNumOfCard() == tempCard[2].getNumOfCard()) 
			{
				type = 3;
				legalCard = true;
				beginNum = tempCard[2].getNumOfCard();
				break;
			}
			legalCard = false;
			break;
		case 4:
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && 
			    tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() &&
			    tempCard[0].getNumOfCard() == tempCard[3].getNumOfCard())
			{
				type = 9;
				legalCard = true;
				beginNum = tempCard[3].getNumOfCard();
				break;
			}
			if ((tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() &&
				 tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard())||
				(tempCard[1].getNumOfCard() == tempCard[2].getNumOfCard() &&
				 tempCard[1].getNumOfCard() == tempCard[3].getNumOfCard())) 
			{
				type = 4;
				legalCard = true;
				beginNum = tempCard[1].getNumOfCard();
				break;
			}
			legalCard = false;
			break;
		case 5:
			if (tempCard[4].getNumOfCard() == tempCard[3].getNumOfCard() - 1 && tempCard[3].getNumOfCard() == tempCard[2].getNumOfCard() - 1 && tempCard[2].getNumOfCard() == tempCard[1].getNumOfCard() - 1 &&
				tempCard[1].getNumOfCard() == tempCard[0].getNumOfCard() - 1 && tempCard[0].getNumOfCard() < 15 && tempCard[4].getNumOfCard() > 2)
			{
				type = 5;
				legalCard = true;
				beginNum = tempCard[4].getNumOfCard();
				break;
			}
			if ((tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() &&
				 tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() && 
				 tempCard[3].getNumOfCard() == tempCard[4].getNumOfCard()) ||
				(tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && 
				 tempCard[2].getNumOfCard() == tempCard[3].getNumOfCard() &&
				 tempCard[2].getNumOfCard() == tempCard[4].getNumOfCard()))
			{
				type = 12;
				legalCard = true;
				beginNum = tempCard[2].getNumOfCard();
				break;
			}
			legalCard = false;
			break;
		case 6:
			if ((tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[3].getNumOfCard()) || 
				(tempCard[1].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[1].getNumOfCard() == tempCard[3].getNumOfCard() && tempCard[1].getNumOfCard() == tempCard[4].getNumOfCard()) || 
				(tempCard[2].getNumOfCard() == tempCard[3].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[4].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[5].getNumOfCard()))
			{
				type = 6;
				legalCard = true;
				beginNum = tempCard[2].getNumOfCard();
				break;
			}
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[3].getNumOfCard() == tempCard[4].getNumOfCard() && tempCard[3].getNumOfCard() == tempCard[5].getNumOfCard() && tempCard[0].getNumOfCard() - 1 == tempCard[3].num && tempCard[0].getNumOfCard() < 15 && tempCard[5].getNumOfCard() > 2) 
			{
				type = 11;
				legalCard = true;
				beginNum = tempCard[5].getNumOfCard();
				break;
			}
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[3].getNumOfCard() && tempCard[4].getNumOfCard() == tempCard[5].getNumOfCard() && tempCard[0].getNumOfCard() - 1 == tempCard[2].getNumOfCard() && tempCard[2].getNumOfCard() - 1 == tempCard[4].getNumOfCard() && tempCard[0].getNumOfCard() < 15 && tempCard[5].getNumOfCard() > 2) 
			{
				type = 7;
				legalCard = true;
				beginNum = tempCard[5].getNumOfCard();
				break;
			}
			if (tempCard[0].getNumOfCard() - 1 == tempCard[1].getNumOfCard() && tempCard[1].getNumOfCard() - 1 == tempCard[2].getNumOfCard() && tempCard[3].getNumOfCard() - 1 == tempCard[4].getNumOfCard() && tempCard[4].getNumOfCard() - 1 == tempCard[5].getNumOfCard()) {
				type = 5;
				legalCard = true;
				beginNum = tempCard[5].getNumOfCard();
				break;
			}
			legalCard = false;
			break;
		default:
			//sequence
			int i = 0;
			if (cardCount % 5 == 0) {
				int[] temp = new int[cardCount];
				int j = 0;
				for (j = 0; j < temp.length; j++) {
					temp[j] = 0;
				}
				j = 0;
				temp[0] = 1;
				for (i = 1; i < cardCount; i++) {
					if (tempCard[i].getNumOfCard() != tempCard[i-1].getNumOfCard())
						j++;
					temp[j]++;
				}
				if (j < cardCount / 5 * 2) {
					j = 0;
					int j2 = 0;
					for (j = 0; j < cardCount / 5 * 2 - cardCount / 5 + 1; j++) {
						for (j2 = 0; j2 < cardCount / 5; j2++) {
							if (temp[j+j2] <= 2) {
								break;
							}
						}
						if (j2 == cardCount / 5) {
							type = 13;
							legalCard = true;
							int sum = 0;
							for (int k = 0; k < j+j2; k++) {
								sum += temp[k];
							}
							beginNum = tempCard[sum-1].getNumOfCard();
							break;
						}
					}
					if (legalCard) {
						break;
					}
				}	
			}
			if (cardCount % 4 == 0) {
				int[] temp = new int[cardCount];
				int j = 0;
				for (j = 0; j < temp.length; j++) {
					temp[j] = 0;
				}
				j = 0;
				temp[0] = 1;
				for (i = 1; i < cardCount; i++) {
					if (tempCard[i].getNumOfCard() != tempCard[i-1].getNumOfCard())
						j++;
					temp[j]++;
				}
				if (j < cardCount / 4 * 2) {
					j = 0;
					int j2 = 0;
					for (j = 0; j < cardCount / 4 * 2 - cardCount / 4 + 1; j++) {
						for (j2 = 0; j2 < cardCount / 4; j2++) {
							if (temp[j+j2] <= 2) {
								break;
							}
						}
						if (j2 == cardCount / 4) {
							type = 8;
							legalCard = true;
							int sum = 0;
							for (int k = 0; k < j+j2; k++) {
								sum += temp[k];
							}
							beginNum = tempCard[sum-1].getNumOfCard();
							break;
						}
					}
					if (legalCard) {
						break;
					}
				}
			}
			if (cardCount % 3 == 0) {
				for (i = 0; i < cardCount - 3; i += 3) {
					if (tempCard[i].getNumOfCard() == tempCard[i+1].getNumOfCard() && tempCard[i].getNumOfCard() == tempCard[i+2].getNumOfCard() && tempCard[i+3].getNumOfCard() == tempCard[i].getNumOfCard() - 1 &&
						tempCard[i+3].getNumOfCard() == tempCard[i+4].getNumOfCard() && tempCard[i+3].getNumOfCard() == tempCard[i+5].getNumOfCard()) {}
					else break;
				}
				if (i == cardCount - 3 && tempCard[0].getNumOfCard() < 15 && tempCard[cardCount-1].getNumOfCard() > 2) {
					type = 11;
					legalCard = true;
					beginNum = tempCard[cardCount-1].getNumOfCard();
					break;
				}
			}
			if (cardCount % 2 == 0) {
				for (i = 0; i < cardCount - 2; i += 2) {
					if (tempCard[i].getNumOfCard() != tempCard[i+1].getNumOfCard() || tempCard[i].getNumOfCard() - 1 != tempCard[i+2].getNumOfCard() || tempCard[i+2].getNumOfCard() != tempCard[i+3].getNumOfCard()) {
						break;
					}
				}
				if (i == cardCount - 2 && tempCard[0].getNumOfCard() < 15 && tempCard[cardCount-1].getNumOfCard() > 2) {
					type = 7;
					legalCard = true;
					beginNum = tempCard[cardCount-1].getNumOfCard();
					break;
				}
			}
			//if (cardCount % 1 == 0)
			for (i = 0; i < cardCount - 1; i++) {
				if (tempCard[i].getNumOfCard() - 1 != tempCard[i+1].getNumOfCard()) {
					break;
				}
			}
			if (i == cardCount - 1 && tempCard[0].getNumOfCard() < 15 && tempCard[cardCount-1].getNumOfCard() > 2) {
				type = 5;
				legalCard = true;
				beginNum = tempCard[cardCount-1].getNumOfCard();
				break;
			}
			legalCard = false;
			break;
		}
	}
	
	void judgeLastType()
	{
		Card[] tempCard = lastCards;
		for(int j = 0; j < lastCardCount -1 ; j++){
			for(int k = 0; k < lastCardCount - j - 1; k++){
				if(tempCard[k].getNumOfCard() < tempCard[k+1].getNumOfCard()){
					int color;
					int num1;
					color = tempCard[k].color;
					num1 = tempCard[k].num;
					tempCard[k].color = tempCard[k+1].color;
					tempCard[k].num = tempCard[k+1].num;
					tempCard[k+1].color = color;
					tempCard[k+1].num = num1;
				}
			}
		}

		lastType = 0;
		switch (lastCardCount) {
		case 0:
			lastType = 0;
			break;
		case 1:
			lastType = 1;
			lastBeginNum = tempCard[0].getNumOfCard();
			break;
		case 2:
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard()) 
			{
				lastType = 2;
				lastBeginNum = tempCard[1].getNumOfCard();
				break;
			}
			else if (tempCard[0].color == 5 && tempCard[1].color == 5)
			{
				lastType = 10;
				break;
			}
			break;
		case 3:
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard()) 
			{
				lastType = 3;
				lastBeginNum = tempCard[2].getNumOfCard();
				break;
			}
			break;
		case 4:
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[3].getNumOfCard())
			{
				lastType = 9;
				lastBeginNum = tempCard[3].getNumOfCard();
				break;
			}
			if ((tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard()) ||
				(tempCard[1].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[1].getNumOfCard() == tempCard[3].getNumOfCard())) 
			{
				lastType = 4;
				lastBeginNum = tempCard[1].getNumOfCard();
				break;
			}
			break;
		case 5:
			if (tempCard[4].getNumOfCard() == tempCard[3].getNumOfCard() - 1 && tempCard[3].getNumOfCard() == tempCard[2].getNumOfCard() - 1 && tempCard[2].getNumOfCard() == tempCard[1].getNumOfCard() - 1 &&
				tempCard[1].getNumOfCard() == tempCard[0].getNumOfCard() - 1 && tempCard[0].getNumOfCard() < 15 && tempCard[4].getNumOfCard() > 2)
			{
				lastType = 5;
				lastBeginNum = tempCard[4].getNumOfCard();
				break;
			}
			if ((tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[3].getNumOfCard() == tempCard[4].getNumOfCard()) ||
				(tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[3].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[4].getNumOfCard()))
			{
				lastType = 12;
				lastBeginNum = tempCard[2].getNumOfCard();
				break;
			}
			break;
		case 6:
			if ((tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[3].getNumOfCard()) || 
				(tempCard[1].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[1].getNumOfCard() == tempCard[3].getNumOfCard() && tempCard[1].getNumOfCard() == tempCard[4].getNumOfCard()) || 
				(tempCard[2].getNumOfCard() == tempCard[3].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[4].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[5].getNumOfCard()))
			{
				lastType = 6;
				lastBeginNum = tempCard[2].getNumOfCard();
				break;
			}
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[0].getNumOfCard() == tempCard[2].getNumOfCard() && tempCard[3].getNumOfCard() == tempCard[4].getNumOfCard() && tempCard[3].getNumOfCard() == tempCard[5].getNumOfCard() && tempCard[0].getNumOfCard() - 1 == tempCard[3].num && tempCard[0].getNumOfCard() < 15 && tempCard[5].getNumOfCard() > 2) 
			{
				lastType = 11;
				lastBeginNum = tempCard[5].getNumOfCard();
				break;
			}
			if (tempCard[0].getNumOfCard() == tempCard[1].getNumOfCard() && tempCard[2].getNumOfCard() == tempCard[3].getNumOfCard() && tempCard[4].getNumOfCard() == tempCard[5].getNumOfCard() && tempCard[0].getNumOfCard() - 1 == tempCard[2].getNumOfCard() && tempCard[2].getNumOfCard() - 1 == tempCard[4].getNumOfCard() && tempCard[0].getNumOfCard() < 15 && tempCard[5].getNumOfCard() > 2) 
			{
				lastType = 7;
				lastBeginNum = tempCard[5].getNumOfCard();
				break;
			}
			break;
		default:
			//sequence
			int i = 0;
			if (lastCardCount % 5 == 0) {
				int[] temp = new int[lastCardCount];
				int j = 0;
				for (j = 0; j < temp.length; j++) {
					temp[j] = 0;
				}
				j = 0;
				temp[0] = 1;
				for (i = 1; i < lastCardCount; i++) {
					if (tempCard[i].getNumOfCard() != tempCard[i-1].getNumOfCard())
						j++;
					temp[j]++;
				}
				if (j >= lastCardCount / 5 * 2) {
					break;
				}
				j = 0;
				int j2 = 0;
				for (j = 0; j < lastCardCount / 5 * 2 - lastCardCount / 5 + 1; j++) {
					for (j2 = 0; j2 < lastCardCount / 5; j2++) {
						if (temp[j+j2] <= 2) {
							break;
						}
					}
					if (j2 == lastCardCount / 5) {
						lastType = 13;
						int sum = 0;
						for (int k = 0; k < j+j2; k++) {
							sum += temp[k];
						}
						lastBeginNum = tempCard[sum-1].getNumOfCard();
						break;
					}
				}
			}
			if (lastCardCount % 4 == 0) {
				int[] temp = new int[lastCardCount];
				int j = 0;
				for (j = 0; j < temp.length; j++) {
					temp[j] = 0;
				}
				j = 0;
				temp[0] = 1;
				for (i = 1; i < lastCardCount; i++) {
					if (tempCard[i].getNumOfCard() != tempCard[i-1].getNumOfCard())
						j++;
					temp[j]++;
				}
				if (j >= lastCardCount / 4 * 2) {
					break;
				}
				j = 0;
				int j2 = 0;
				for (j = 0; j < lastCardCount / 4 * 2 - lastCardCount / 4 + 1; j++) {
					for (j2 = 0; j2 < lastCardCount / 4; j2++) {
						if (temp[j+j2] <= 2) {
							break;
						}
					}
					if (j2 == lastCardCount / 4) {
						lastType = 8;
						int sum = 0;
						for (int k = 0; k < j+j2; k++) {
							sum += temp[k];
						}
						lastBeginNum = tempCard[sum-1].getNumOfCard();
						break;
					}
				}
			}
			if (lastCardCount % 3 == 0) {
				for (i = 0; i < lastCardCount - 3; i += 3) {
					if (tempCard[i].getNumOfCard() == tempCard[i+1].getNumOfCard() && tempCard[i].getNumOfCard() == tempCard[i+2].getNumOfCard() && tempCard[i+3].getNumOfCard() == tempCard[i].getNumOfCard() - 1 &&
						tempCard[i+3].getNumOfCard() == tempCard[i+4].getNumOfCard() && tempCard[i+3].getNumOfCard() == tempCard[i+5].getNumOfCard()) {}
					else break;
				}
				if (i == lastCardCount - 3 && tempCard[0].getNumOfCard() < 15 && tempCard[lastCardCount-1].getNumOfCard() > 2) {
					lastType = 11;
					lastBeginNum = tempCard[lastCardCount-1].getNumOfCard();
					break;
				}
			}
			if (lastCardCount % 2 == 0) {
				for (i = 0; i < lastCardCount - 2; i += 2) {
					if (tempCard[i].getNumOfCard() != tempCard[i+1].getNumOfCard() || tempCard[i].getNumOfCard() - 1 != tempCard[i+2].getNumOfCard() || tempCard[i+2].getNumOfCard() != tempCard[i+3].getNumOfCard()) {
						break;
					}
				}
				if (i == lastCardCount - 2 && tempCard[0].getNumOfCard() < 15 && tempCard[lastCardCount-1].getNumOfCard() > 2) {
					lastType = 7;
					lastBeginNum = tempCard[lastCardCount-1].getNumOfCard();
					break;
				}
			}
			//if (lastCardCount % 1 == 0)
			for (i = 0; i < lastCardCount - 1; i++) {
				if (tempCard[i].getNumOfCard() - 1 != tempCard[i+1].getNumOfCard()) {
					break;
				}
			}
			if (i == lastCardCount - 1 && tempCard[0].getNumOfCard() < 15 && tempCard[lastCardCount-1].getNumOfCard() > 2) {
				lastType = 5;
				lastBeginNum = tempCard[lastCardCount-1].getNumOfCard();
				break;
			}
			break;
		}
	}
	
	void play()
	{
		playCard = false;
		if (!legalCard) {
			playCard = false;
			return;
		}
		if (lastType == 0) {
			playCard = true;
			return;
		}
		switch (type) {
		case 9:
			if (lastType != 9 && lastType != 10) {
				playCard = true;
				break;
			}
			if (lastType == 9) {
				if (beginNum > lastBeginNum) {
					playCard = true;
					break;
				}
			}
			if (lastType == 10) {
				playCard = false;
				break;
			}
			playCard = false;
			break;
		case 10:
			playCard = true;
			break;
		default:
			if (type != lastType) {
				playCard = false;
				break;
			}
			if (beginNum > lastBeginNum) {
				playCard = true;
				break;
			}
			playCard = false;
			break;
		}
		return;
	}
}