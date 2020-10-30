package org.tcs.hackathon.kart.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.tcs.hackathon.kart.model.Kart;

import io.quarkus.vertx.ConsumeEvent;

@ApplicationScoped
public class KartService {
	
	@ConsumeEvent(value = "delete-kart", blocking = true)
    @Transactional
    public void deleteKart(String userId) {
        //LocalDate birth = LocalDate.now().plusWeeks(Math.round(Math.floor(Math.random() * 20 * 52 * -1)));
        //EyeColor color = EyeColor.values()[(int)(Math.floor(Math.random() * EyeColor.values().length))];
		Kart kartObj = Kart.findByUserId(userId);
		kartObj.delete();
    }
}
