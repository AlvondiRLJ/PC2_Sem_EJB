/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author Alvondi
 */
public class GenericDAO<T> implements Serializable {
    
    private Class persistentClass;
    private String filter = "";
    private String customFilter;
    private List<Order> listOrder = new ArrayList<>();
    private Order currentOrder;
    private int maxObjects = 10;
    private int position = 0;
    private int totalObjects = 0;
    private ConverterOrder converterOrder;
    private List<T> listAll;
    private List<T> listObjects;

    public GenericDAO() {
    }
    
    public void persist(T object) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public void merge(T objeto) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public void remove(Integer id) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            T objeto = (T) em.find(persistentClass, id);
            em.merge(objeto);
            em.remove(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public T getObjectById(Integer id) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            return (T) em.find(persistentClass, id);
        } catch (Exception e) {
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public void refresh(T object) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.refresh(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }
    }
    
    public List<T> listObjects() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder ejbql = new StringBuilder("from ");
            ejbql.append(persistentClass.getSimpleName());
            StringBuilder filtroTemp = new StringBuilder("");
            //tratar o filtro da consulta
            // verifico se tem algum filtro manual       
            if ((customFilter != null) && (customFilter.length() > 0)) {
                // prevenindo sql injection
                customFilter = removeCaracteres(customFilter, "'");
                ejbql.append(" ");
                ejbql.append(customFilter);
                ejbql.append(" ");
                filtroTemp.append(" ");
                filtroTemp.append(customFilter);
                filtroTemp.append(" ");
            } else //nao tendo uso o filtro do controle       
            if ((filter != null) && (filter.length() > 0)) {
                // prevenindo sql injection
                filter = removeCaracteres(filter, "'");
                switch (currentOrder.getOperator()) {
                    case "=":
                        // tratamento para caso digitem com id selecionado algo que não é numero não gerar exceção
                        if (currentOrder.getAttribute().equals("id")) {
                            try {
                                Integer i = Integer.parseInt(filter);
                            } catch (Exception e) {
                                filter = "0";
                            }
                        }
                        filtroTemp.append(" where ");
                        filtroTemp.append(currentOrder.getAttribute());
                        filtroTemp.append(" = '");
                        filtroTemp.append(filter);
                        filtroTemp.append("' ");
                        ejbql.append(filtroTemp);
                        break;
                    case "like":
                        filtroTemp.append(" where upper(");
                        filtroTemp.append(currentOrder.getAttribute());
                        filtroTemp.append(") like '");
                        filtroTemp.append(filter.toUpperCase());
                        filtroTemp.append("%' ");
                        ejbql.append(filtroTemp.toString());
                        break;
                }
            }
            if (currentOrder != null) {
                ejbql.append(" order by ");
                ejbql.append(currentOrder.getAttribute());
            }
            // calcula o total de objetos da lista fazendo uma consulta trazendo somente o id para ficar rápido
            StringBuilder ejbqlTemp = new StringBuilder("select id from ");
            ejbqlTemp.append(persistentClass.getSimpleName());
            ejbqlTemp.append(filtroTemp.toString());
            // System.out.println("ejbqlTemp: "+ejbqlTemp);
            totalObjects = em.createQuery(ejbqlTemp.toString()).getResultList().size();
            // testo se maximo objetos = 0 trago todos os registros
            if (maxObjects == 0) {
                maxObjects = totalObjects;
            }
            // se o resultado não é maior que uma pagina coloca a navegação na primeira pagina novamente
            if (totalObjects <= maxObjects) {
                position = 0;
            }
            //retorna uma consulta paginada   
            return em.createQuery(ejbql.toString()).
                    setMaxResults(maxObjects).
                    setFirstResult(position).
                    getResultList();
        } catch (Exception e) {
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

    }
    
    public List<T> listAll() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GenContracts_SemEJBPU");
        EntityManager em = emf.createEntityManager();
        try {
            StringBuilder ejbql = new StringBuilder("from ");
            ejbql.append(persistentClass.getSimpleName());
            if (currentOrder != null) {
                ejbql.append(" order by ");
                ejbql.append(currentOrder.getAttribute());
            }
            return em.createQuery(ejbql.toString()).getResultList();
        } catch (Exception e) {
            throw new Exception("Erro na operação de persistência: " + e.getMessage());
        } finally {
            em.close();
            emf.close();
        }

    }
    
    public void first() {
        position = 0;
    }

    public void previous() {
        position = position - maxObjects;
        if (position < 0) {
            position = 0;
        }
    }

    public void next() {
        if ((position + maxObjects) < totalObjects) {
            position = position + maxObjects;
        }
    }

    public void last() {
        int resto = (totalObjects % maxObjects);
        if (resto > 0) {
            position = totalObjects - resto;
        } else {
            position = totalObjects - maxObjects;
        }
    }
    
    public String getNavigationMessage() {
        int ate = position + maxObjects;
        if (ate > totalObjects) {
            ate = totalObjects;
        }
        StringBuffer stb = new StringBuffer();
        stb.append("Listando de");
        stb.append(" ");
        stb.append((position + 1));
        stb.append(" ");
        stb.append("até");
        stb.append(" ");
        stb.append(ate);
        stb.append(" ");
        stb.append("de");
        stb.append(" ");
        stb.append(totalObjects);
        stb.append(" ");
        stb.append("registros");
//        return "Listando de " + (position + 1)
//                + " até " + ate + " de " + totalObjects + " registros";
        return stb.toString();
    }
    
    public static String removeCaracteres(String texto, String caracteres) {
        String str = texto;
        StringBuilder caracteresTemp = new StringBuilder("[");
        caracteresTemp.append(caracteres);
        caracteresTemp.append("]");
        str = texto.replaceAll(caracteresTemp.toString(), "");
        return str;
    }

    public Class getPersistentClass() {
        return persistentClass;
    }

    public void setPersistentClass(Class persistentClass) {
        this.persistentClass = persistentClass;
    }

    public List<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public int getMaxObjects() {
        return maxObjects;
    }

    public void setMaxObjects(int maxObjects) {
        this.maxObjects = maxObjects;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTotalObjects() {
        return totalObjects;
    }

    public void setTotalObjects(int totalObjects) {
        this.totalObjects = totalObjects;
    }

    public ConverterOrder getConverterOrder() {
        return converterOrder;
    }

    public void setConverterOrder(ConverterOrder converterOrder) {
        this.converterOrder = converterOrder;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getCustomFilter() {
        return customFilter;
    }

    public void setCustomFilter(String customFilter) {
        this.customFilter = customFilter;
    }

    public List<T> getListAll() throws Exception {
        return listAll();
    }

    public List<T> getListObjects() throws Exception {
        return listObjects();
    }
    
}
