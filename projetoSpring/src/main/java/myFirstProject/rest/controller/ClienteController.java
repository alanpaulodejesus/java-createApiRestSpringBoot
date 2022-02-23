package myFirstProject.rest.controller;

import myFirstProject.domain.entity.Cliente;
import myFirstProject.domain.repository.Clientes;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<Cliente>cliente=clientes.findById(id);
        if (cliente.isPresent()){
            return ResponseEntity.ok(cliente.get()); //==> Retorna Cliente com status ok
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente>cliente=clientes.findById(id);
        if (cliente.isPresent()){
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //@RequestMapping(method = RequestMethod.PUT)
    @PutMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Cliente cliente){

        return clientes
                .findById(id)
                .map(clienteExistente-> {
                    cliente.setId(clienteExistente.getId()); //--> Apenas o map pode buscar todos as informações do cliente pelo id
                    clientes.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()->ResponseEntity.notFound().build());

    }
}
