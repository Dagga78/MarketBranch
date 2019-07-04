/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Singleton
@Startup
public class PanierService {

    @PersistenceContext(unitName = "TeamworkPU", type = PersistenceContextType.TRANSACTION)
    EntityManager em;

    public void addPanier(model.Panier p) {
        em.persist(p);
    }
}
