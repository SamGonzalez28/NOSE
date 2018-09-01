<?php


namespace App\Http\Controllers;

use App\Models\Cartera;
use App\Models\Cliente;
use App\Models\Local;
use Illuminate\Http\Request;

/**
 * Description of CarteraController
 * Clase usada para Controlar las funciones de Cartera
 * @author alejo
 */
class CarteraController extends Controller {
    
    /**
    *	Constructor para los metodos que necesitan autenticacion
    **/
    function __construct() {
        $this->middleware('auth', ['only' => [
                'listar','modificar'
        ]]);
    }
    
    /**
    *	Funcion para registrar cartera	
    **/
    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $cliente = \App\Models\Cliente::where("external_id", $data["cliente"])->first();
                $local = \App\Models\Local::where("external_id", $data["local"])->first();
                if ($cliente == true && $local == true) {
                    $cartera = new Cartera();
                    $cartera->Local()->associate($local);
                    $cartera->Cliente()->associate($cliente);
                    $cartera->external_id = utilidades\UUID::v4();
                    $cartera->saldo = $data["saldo"];
                    $cartera->save();
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
    *	Funcion para modificar cartera	
    **/
    public function modificar(Request $request, $external_id) {//el request es solo para post, get y put
        $carObj = Cartera::where("external_id", $external_id)->first(); //busca el registro por el primer external_id

        if ($carObj) {//verifica que exista el administrador
            if ($request->isJson()) {//verifica que este en formato json
                $data = $request->json()->all(); //estrae todo del administrador
                $cartera = Cartera::find($carObj->id); //para que busque el id con el external id

                if (isset($data["saldo"]))
                    $cartera->saldo = $data["saldo"];

                $cartera->save();
                return response()->json(["mensaje" => "Operacion Exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "Formato Inadecuado", "siglas" => "FI"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No registros", "siglas" => "NR"], 203);
        }
    }
    
    /**
    *	Funcion para listar cartera	
    */
    public function Listar() {
        $lista = Cartera::orderBy('id_local')->get();
        $data = array();

        foreach ($lista as $value) {
            $local = Local::where('id',$value->id_local)->first();
            $cliente = Cliente::where('id',$value->id_cliente)->first();
            $data[] = ["Cliente" => $cliente->nombres, "local" => $local->nombre, "saldo" => $value->saldo];
        }
        return $data;
    }

}
