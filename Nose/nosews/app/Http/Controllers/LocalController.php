<?php

namespace App\Http\Controllers;

use App\Models\Local;
use Illuminate\Http\Request;

/**
 * Description of LocalController
 * Clase usada para controlar las funciones que se aplican a la tabla local
 * @author AlejandroC
 */
class LocalController extends Controller {

     /**
     *Variable que guarda el external id, que reciben las funciones que aqui se usan 
     * @var type 
     */
    private $external_id;

    /**
     * Constructor para controlar que funciones necesitan autenticacion
     */
    function __construct() {
        $this->middleware('auth', ['only' =>
            [
                'modificar', 'eliminar'
            ]
        ]);
    }

    /**
     * Funcion que se encarga de registrar un Local
     * @param Request $request
     * @return type
     */
    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $local = new Local();
                $local->nombre = $data["nombre"];
                $local->direccion = $data["direccion"];
                $local->ruc = $data["ruc"];
                $local->estado = true;
                $local->clave = $data["clave"];
                $local->telefono = $data["telefono"];
                $local->external_id = utilidades\UUID::v4();

                $local->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } catch (Exception $ex) {
                return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
                echo "Faltan Datos";
            }
        } else {
            return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 404);
        }
    }
    
    /**
     * Funcion para que local inicie sesion
     * @param Request $request
     * @return type
     */
    public function inicioSesion(Request $request) {

        try {
            $data = $request->json()->all();
            $local = Local::where("nombre", $data["nombre"])->where("clave", $data["clave"])->where("estado", true)->first();

            if ($local) {
                return response()->json([
                            "nombre" => $local->nombre,
                            "external_id" => $local->external_id,
                            "token" => base64_encode($local->external_id . '--' . $local->nombre),
                            "mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
            }
        } catch (\Exception $exc) {
            return response()->json(["mensaje" => "Faltan Datos", "siglas" => "FD"], 400);
        }
    }
    
    /**
     * Funcion para modificar los datos de local
     * @param Request $request
     * @param type $external_id el external de local que se va a modificar
     * @return type
     */
    public function modificar(Request $request, $external_id) {
        $localObjeto = Local::where("external_id", $external_id)->first();
        if ($localObjeto) {
            if ($request->isJson()) {
                $data = $request->json()->all();
                $local = Local::find($localObjeto->id);
                if (isset($data["nombre"]))
                    $local->nombre = $data["nombre"];
                if (isset($data["direccion"]))
                    $local->direccion = $data["direccion"];
                if (isset($data["ruc"]))
                    $local->ruc = $data["ruc"];
                if (isset($data["clave"]))
                    $local->clave = $data["clave"];
                if (isset($data["telefono"]))
                    $local->telefono = $data["telefono"];

                $local->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            }else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NED"], 204);
        }
    }

    /**
     * Funcion para eliminar de manera logica, un perfil de local
     * @param Request $request
     * @param type $external_id del local que se va a eliminar
     * @return type
     */
    public function eliminar(Request $request, $external_id) {
        $localObjeto = Local::where("external_id", $external_id)->first();
        if ($localObjeto) {
            if ($request->isJson()) {

                $local = Local::find($localObjeto->id);

                $local->estado = false;
                $local->save();
                MenuController::eliminar($external_id);
                ClienteController::eliminar($external_id);
                CarteraController::eliminar($external_id);
                SesionController::eliminar($external_id);

                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

    /**
     * Funcion para listar todos los locales con perfil activo(que no haya sido eliminado)
     * @return type
     */
    public function Listar() {
        $lista = Local::where('estado','=','1')->orderBy('nombre')->get();
        $data = array();

        foreach ($lista as $value) {

            $data[] = ["nombre" => $value->nombre, "ruc" => $value->ruc, "direccion" => $value->direccion,
                "telefono" => $value->telefono, "external_id"=>$value->external_id];
        }
        return $data;
    }
    
    /**
     * Funcion para ver los datos del local que ha iniciado sesion
     * @param type $external_id del cliente que inicio sesion
     * @return type
     */ 
    public function ver($external_id) {
        $local = Local::where("external_id", $external_id)->get();
        $data = array();
        
          
            foreach ($local as $value) {
                $data[] = ["nombre" => $value->nombre, "ruc" => $value->ruc, "direccion" => $value->direccion,
                "telefono" => $value->telefono];
            }
            return $data;
        
        
    }

}
