package model.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "matriculas")
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "curso_id", nullable = false)
    private Curso curso;
    
    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusMatricula status;
    
    // Enum para status da matrícula
    public enum StatusMatricula {
        ATIVA,
        TRANCADA,
        CONCLUIDA,
        CANCELADA
    }
    
    // Construtores
    public Matricula() {
        this.dataMatricula = LocalDate.now();
        this.status = StatusMatricula.ATIVA;
    }
    
    public Matricula(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
        this.dataMatricula = LocalDate.now();
        this.status = StatusMatricula.ATIVA;
    }
    
    // Getters e Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Curso getCurso() {
        return curso;
    }
    
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public LocalDate getDataMatricula() {
        return dataMatricula;
    }
    
    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }
    
    public StatusMatricula getStatus() {
        return status;
    }
    
    public void setStatus(StatusMatricula status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", aluno=" + (aluno != null ? aluno.getNome() : "null") +
                ", curso=" + (curso != null ? curso.getNome() : "null") +
                ", dataMatricula=" + dataMatricula +
                ", status=" + status +
                '}';
    }
}
