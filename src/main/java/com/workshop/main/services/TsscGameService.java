package com.workshop.main.services;

import com.workshop.main.model.TsscGame;

public interface TsscGameService {
	
	public TsscGame setGame(TsscGame game, int groups, String name);
	public TsscGame addGameT(TsscGame game1, long id) ;
	public TsscGame addGame(TsscGame game1);
	public TsscGame addGame2(TsscGame game1, long id) ;
	public TsscGame findGame(Long id); 

}
