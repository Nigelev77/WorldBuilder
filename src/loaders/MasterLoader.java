package loaders;

import models.StaticModel;

public class MasterLoader {
	
	
	public StaticModel renderStaticModel(String fileName) {
		return new StaticModel(SingleFaceWaveFrontLoader.loadEntity(fileName));
	}
}
