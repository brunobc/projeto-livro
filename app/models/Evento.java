package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;
import validations.FromNow;

@Entity
public class Evento {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evento other = (Evento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Id
	@GeneratedValue
	private Integer id;
	@Email
	private String emailParaContato;
	@Enumerated(EnumType.STRING)
	private Estado estado;
	@Column(columnDefinition = "text")
	@NotBlank
	private String descricao;
	@URL
	private String site;
	private String twitter;
	@Required
	private String nome;
	@FromNow	
	private Calendar dataDeInicio;
	private Calendar dataDeFim;
	private String caminhoImagem;
	private boolean aprovado;
		

	public List<ValidationError> validate() {
		ArrayList<ValidationError> errors = new ArrayList<ValidationError>();
		if (dataDeFim == null) {
			this.dataDeFim = (Calendar) dataDeInicio.clone();
			return null;
		}
		if (!dataDeFim.after(dataDeInicio)) {
			errors.add(new ValidationError("dataDeFim", "O fim deve ser após o inicio"));			
		}

		return errors.isEmpty() ? null : errors;
	}
	
	public boolean taRolando(){
		Calendar hoje = Calendar.getInstance();
		return hoje.after(this.dataDeInicio) && hoje.before(dataDeFim);
	}
	
	public boolean temLogo(){
		return caminhoImagem!=null;
	}
	
	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Calendar getDataDeFim() {
		return dataDeFim;
	}

	public void setDataDeFim(Calendar dataDeFim) {
		this.dataDeFim = dataDeFim;
	}

	public String getCaminhoImagem() {
		return caminhoImagem;
	}

	public void setCaminhoImagem(String caminhoImagem) {
		this.caminhoImagem = caminhoImagem;
	}

	public Calendar getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(Calendar dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmailParaContato() {
		return emailParaContato;
	}

	public void setEmailParaContato(String emailParaContato) {
		this.emailParaContato = emailParaContato;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", emailParaContato=" + emailParaContato
				+ ", estado=" + estado + ", descricao=" + descricao + ", site="
				+ site + ", twitter=" + twitter + ", nome=" + nome
				+ ", dataDeInicio=" + dataDeInicio.getTime() + "]";
	}

}
