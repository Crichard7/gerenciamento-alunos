package br.com.gerenciamento.controller;

import br.com.gerenciamento.enums.Curso;
import br.com.gerenciamento.enums.Status;
import br.com.gerenciamento.enums.Turno;
import br.com.gerenciamento.model.Aluno;
import br.com.gerenciamento.service.ServiceAluno;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ServiceAluno serviceAluno;

    private Aluno aluno;

    @Before
    public void setUp() {
        aluno = new Aluno();
        aluno.setNome("Teste Aluno");
        aluno.setMatricula("123");
        aluno.setCurso(Curso.DIREITO);
        aluno.setStatus(Status.ATIVO);
        aluno.setTurno(Turno.MATUTINO);

        serviceAluno.save(aluno);
    }

    @Test
    public void testInsertAlunosPage() throws Exception {
        mockMvc.perform(get("/inserirAlunos"))
                .andExpect(status().isOk())
                .andExpect(view().name("Aluno/formAluno"))
                .andExpect(model().attributeExists("aluno"));
    }
}
