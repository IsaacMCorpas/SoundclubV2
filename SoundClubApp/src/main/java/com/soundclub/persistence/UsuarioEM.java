package com.soundclub.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.soundclub.models.Usuario;

public class UsuarioEM extends EntityManager{

	private static UsuarioEM instance = null;
	
	protected UsuarioEM() {
		super();
	}
	
	public static final UsuarioEM getInstance() {
		if (instance == null) {
			instance = new UsuarioEM();
			
		}
		return instance;
		
	}
	
	public Usuario getUsuarioEmailPass( String email , String pass) {
		Usuario user=null;
		try {
			Session session = factory.openSession();
			Transaction t=session.beginTransaction();
			String sql="FROM Usuario WHERE mail='"+email+"' AND password='"+pass+"' ";
			user=session.createQuery(sql,Usuario.class).getSingleResult();
			t.commit();
			session.close();
		} catch (Exception e) {
			ManejoException(e);
			
		}
		
		return user;
	}
	
	public Usuario getUsuarioEmail( String email) {
		Usuario user=null;
		try {
			Session session = factory.openSession();
			Transaction t=session.beginTransaction();
			String sql="FROM Usuario WHERE email='"+email+"'";
			user=session.createQuery(sql,Usuario.class).getSingleResult();
			t.commit();
			session.close();
		} catch (Exception e) {
			ManejoException(e);
			
		}
		
		return user;
	}
	
	public Usuario getUsuarioID( int idUsuario) {
		Usuario user=null;
		try {
			Session session = factory.openSession();
			Transaction t=session.beginTransaction();
			String sql="FROM Usuario WHERE uid="+idUsuario;
			user=session.createQuery(sql,Usuario.class).getSingleResult();
			t.commit();
			session.close();
		} catch (Exception e) {
			ManejoException(e);
			
		}
		
		return user;
	}
	
	public List<Usuario> getListaUsuarioBusqueda( String nombre) {
		List<Usuario>  listaUser=null;
		try {
			Session session = factory.openSession();
			Transaction t=session.beginTransaction();
			String sql="FROM Usuario WHERE nombre LIKE '"+nombre+"%'";
			listaUser=session.createQuery(sql,Usuario.class).getResultList();
			t.commit();
			session.close();
			
		} catch (Exception e) {
			ManejoException(e);
			
		}
		
		return listaUser;
	}
	
	private void ManejoException(Exception e) {
		System.out.println("Ha ocurrido un erro en la entity Manager de Usuario ::"+e);
		e.printStackTrace();
		
	}
	
	
	public boolean save(Usuario reg) {
		try {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		
		session.save(reg);

		t.commit();
		session.close();
		} catch (Exception e) {
			ManejoException(e);
			
		}
		return true;

	}
	public boolean update(Usuario actu) {
		try {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		
		session.update(actu);

		t.commit();
		session.close();
		} catch (Exception e) {
			ManejoException(e);
			
		}
		return true;

	}

	
	//////Testing////////
	public List<Usuario> getLista() {
		List<Usuario>  listaUser=null;
		try {
			Session session = factory.openSession();
			Transaction t=session.beginTransaction();
			
			listaUser = session.createQuery("FROM Usuario", Usuario.class).getResultList();
			
			t.commit();
			session.close();
			
		} catch (Exception e) {
			ManejoException(e);
		}
		return listaUser;
	}
	////////////////////
}
