package br.com.gabrielmontoni.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielmontoni.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel modeloTarefa, HttpServletRequest request) {
        System.out.println("Chegou no controller" + request.getAttribute("idUser"));
        modeloTarefa.setIdUser((UUID) request.getAttribute("idUser"));

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(modeloTarefa.getStartAt()) || currentDate.isAfter(modeloTarefa.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data informada deve ser maior do que a data atual");
        }

        if (modeloTarefa.getStartAt().isAfter(modeloTarefa.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio deve ser menor do que a data de termino");
        }

        var task = this.taskRepository.save(modeloTarefa);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUsuario = request.getAttribute("idUser");
        var listaTarefas = this.taskRepository.findByIdUser((UUID) idUsuario);
        return listaTarefas;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel modeloTarefa, HttpServletRequest request,
            @PathVariable UUID id) {
        var task = this.taskRepository.findById(id).orElse(null);
        var idUsuario = request.getAttribute("idUser");

        if(task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tarefa inexistente");
        }

        var currentDate = LocalDateTime.now();
        if (currentDate.isAfter(modeloTarefa.getStartAt()) || currentDate.isAfter(modeloTarefa.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data informada deve ser maior do que a data atual");
        }

        if (modeloTarefa.getStartAt().isAfter(modeloTarefa.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de inicio deve ser menor do que a data de termino");
        }

        if (!idUsuario.equals(task.getIdUser())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O Usuário deve ser o mesmo que cadastrou a tarefa");
        }

        Utils.copyNonNullProperties(modeloTarefa, task);

        var tarefa = this.taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(tarefa);
    }
}
