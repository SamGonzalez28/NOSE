<?php

namespace App\Http\Controllers;

use App\Models\Registros;
use App\Models\Cliente;
use App\Models\Local;
use App\Models\Menu;
use Illuminate\Http\Request;

/**
 * Description of RegistrosController
 * Clase usada para controlar las funciones que se aplican a la tabla registros
 * @author alejo
 */
class RegistrosController extends Controller {

    /**
     * Variable que guarda el external id, que reciben las funciones que aqui se usan 
     * @var type 
     */
    private $external_id;

    /**
     * Constructor para controlar que funciones necesitan autenticacion
     */
    function __construct() {
        $this->middleware('auth', ['only' => [
                'listarporcliente'
        ]]);
    }
    
    /**
     * Funcion que se encarga de registrar un registro, aunque suene redundante
     * @param Request $request
     * @return type
     */
    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = \App\Models\Cliente::where("external_id", $data["cliente"])->first();
                $menu = \App\Models\Menu::where("external_id", $data["menu"])->first();
                $local = \App\Models\Local::where("external_id", $data["local"])->first();
                if ($cliente == true && $menu == true && $local == true) {
                    $regi = new Registros();
                    $regi->Cliente()->associate($cliente);
                    $regi->Menu()->associate($menu);
                    $regi->Local()->associate($local);
                    $regi->cantidad = $data["cantidad"];
                    $regi->valor = $data["valor"];

                    $regi->external_id = utilidades\UUID::v4();

                    $regi->save();
                    return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
                } else {
                    return response()->json(["mensaje" => "Referencia Incorrecta", "siglas" => "RI"], 400);
                }
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }

    /**
     * Funcion para listar registros, de acuerdo al cliente al que estan asociados
     * @param type $external_id del cliente
     * @return type
     */
    public function listarporCliente($external_id) {
        $cliente = \App\Models\Cliente::where("external_id", $external_id)->first();
        
        if ($cliente) {
            $lista = Registros:: where('id_cliente', $cliente->id)
            ->join('menu', 'menu.id', '=', 'registros.id_menu')
            ->join('local', 'local.id', '=', 'registros.id_local')
            ->orderBy('fecha', 'des')->get();
            
            foreach ($lista as $item) {
                $data[] = ["cliente" => $cliente->user,"menu" => $item->descripcion,"local"=>$item->nombre, "cantidad" => $item->cantidad,"precio" => $item->precio,  "valor" => $item->valor, "fecha"=>$item->fecha,"external_id"=>$item->external_id];
            }
            return response()->json($data, 200);
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }
    
    /**
     * Funcion para listar registros, de acuerdo al local al que estan asociados
     * @param type $external_id del local
     * @return type
     */
    public function listarporLocal($external_id) {
        $local = \App\Models\Local::where("external_id", $external_id)->first();
        
        if ($local) {
            $lista = Registros:: where('id_local', $local->id)->get();
            foreach ($lista as $item) {
                $menu = \App\Models\Menu::where('id', $item->id_menu)->first();
                $cliente = \App\Models\Cliente::where('id', $item->id_cliente)->first();
                
                $data[] = [
                "cliente"=>$cliente->user,
                "menu" => $menu->descripcion, 
                "local" => $local->nombre,
                "precio"=> $menu ->precio,
                "cantidad" => $item->cantidad, 
                "valor" => $item->valor, 
                "fecha"=>$item->fecha,
                "external_id"=>$item->external_id];
            }
            return response()->json($data, 200);
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

    /**
     * Lista todos los registros
     * @return type
     */
    public function listar() {
        $lista = Registros::orderBy('id_cliente')->get();
        $data = array();

        foreach ($lista as $value) {
            $menu = \App\Models\Menu::where('id', $value->id_menu)->first();
            $cliente = Cliente::where('id', $value->id_cliente)->first();
            $local = Local::where('id', $value->id_local)->first();
            $data[] = ["cliente" => $cliente->user, "menu" => $menu->tipo, "descripcion" => $menu->descripcion,
                "cantidad" => $value->cantidad, "fecha" => $value->fecha, "valor" => $value->valor, "local" => $local->nombre];
        }
        return $data;
    }

}
