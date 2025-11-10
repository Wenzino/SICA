import model.dao.AlunoDAO;
import model.dao.CursoDAO;
import model.dao.DisciplinaDAO;
import model.dao.MatriculaDAO;
import model.entities.Aluno;
import model.entities.Curso;
import model.entities.Disciplina;
import model.entities.Matricula;
import util.JPAUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe de teste para verificar se o backend esta funcionando corretamente
 * Execute esta classe para testar a conexao com o banco de dados
 */
public class TestBackend {
    
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("  TESTE DO BACKEND - SISTEMA ACADeMICO");
        System.out.println("===========================================\n");
        
        try {
            // 1. Testar conexao JPA (as credenciais sao carregadas automaticamente)
            System.out.println("1. Testando conexao JPA...");
            if (JPAUtil.isOpen()) {
                System.out.println("Conexao JPA estabelecida com sucesso!\n");
            }
            
            // 2. Criar DAOs
            System.out.println("2. Criando DAOs...");
            AlunoDAO alunoDAO = new AlunoDAO();
            CursoDAO cursoDAO = new CursoDAO();
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            MatriculaDAO matriculaDAO = new MatriculaDAO();
            System.out.println("DAOs criados com sucesso!\n");
            
            // 3. Testar operacoes CRUD
            System.out.println("3. Testando operacoes CRUD...\n");
            
            // Criar e salvar um curso
            System.out.println("  -> Criando curso...");
            Curso curso = new Curso("Engenharia de Software", "ENSO-01");
            curso.setDescricao("Curso de Engenharia de Software");
            curso.setCargaHoraria(3600);
            curso = cursoDAO.save(curso);
            System.out.println("  -> Curso salvo: " + curso);
            
            // Criar e salvar uma disciplina
            System.out.println("\n  -> Criando disciplina...");
            Disciplina disciplina = new Disciplina("Programacao Orientada a Objetos", "POO-101", 60);
            disciplina.setEmenta("Conceitos de POO em Java");
            disciplina = disciplinaDAO.save(disciplina);
            System.out.println("   ✓ Disciplina salva: " + disciplina);
            
            // Criar e salvar um aluno
            System.out.println("\n  -> Criando aluno...");
            Aluno aluno = new Aluno("João Silva", "2024001", "joao.silva@email.com");
            aluno.setDataNascimento(LocalDate.of(2000, 5, 15));
            aluno.setTelefone("11999999999");
            aluno = alunoDAO.save(aluno);
            System.out.println("  -> Aluno salvo: " + aluno);
            
            // Criar e salvar uma matricula
            System.out.println("\n  -> Criando matricula...");
            Matricula matricula = new Matricula(aluno, curso);
            matricula = matriculaDAO.save(matricula);
            System.out.println("    Matricula salva: " + matricula);
            
            // 4. Testar consultas
            System.out.println("\n4. Testando consultas...\n");
            
            System.out.println("  -> Listando todos os alunos:");
            List<Aluno> alunos = alunoDAO.findAll();
            alunos.forEach(a -> System.out.println("     - " + a));
            
            System.out.println("\n  -> Listando todos os cursos:");
            List<Curso> cursos = cursoDAO.findAll();
            cursos.forEach(c -> System.out.println("     - " + c));
            
            System.out.println("\n  -> Buscando aluno por matricula:");
            Aluno alunoEncontrado = alunoDAO.findByMatricula("2024001");
            System.out.println("     - " + alunoEncontrado);
            
            System.out.println("\n  -> Listando matriculas do aluno:");
            List<Matricula> matriculasAluno = matriculaDAO.findByAluno(aluno.getId());
            matriculasAluno.forEach(m -> System.out.println("     - " + m));
            
            // 5. Estatisticas
            System.out.println("\n5. Estatisticas do banco de dados:");
            System.out.println("   - Total de alunos: " + alunoDAO.count());
            System.out.println("   - Total de cursos: " + cursoDAO.count());
            System.out.println("   - Total de disciplinas: " + disciplinaDAO.count());
            System.out.println("   - Total de matriculas: " + matriculaDAO.count());
            
            System.out.println("\n===========================================");
            System.out.println("TODOS OS TESTES PASSARAM COM SUCESSO!");
            System.out.println("===========================================");
            
        } catch (Exception e) {
            System.err.println("\nX ERRO durante os testes:");
            System.err.println("  " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Fechar EntityManagerFactory
            JPAUtil.close();
            System.out.println("\n Recursos liberados. Teste finalizado.");
        }
    }
}
