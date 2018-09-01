<?php


namespace App\Http\Controllers;

use App\Models\Menu;
use App\Models\Local;
use Illuminate\Http\Request;

/**
 * Description of MenuController
 * Clase usada para controlar las funciones que se aplican a la tabla menu
 * @author AlejandroC
 */
class MenuController extends Controller {

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
                 'modificar','eliminar'
            ]
        ]);
    }
    
    /**
     * Funcion que se encarga de registrar un Menu
     * @param Request $request
     * @return type
     */
    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $local = \App\Models\Local::where("external_id", $data["local"])->first();
                if ($local) {
                    $menu = new Menu();
                    $menu->tipo = $data["tipo"];
                    $menu->precio = $data["precio"];
                    $menu->descripcion = $data["descripcion"];
                    $menu->external_id = utilidades\UUID::v4();
                    $menu->Local()->associate($local);

                    $menu->save();
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
     * Funcion para modificar los datos de menu
     * @param Request $request
     * @param type $external_id el external de menu que se va a modificar
     * @return type
     */
    public function modificar(Request $request, $external_id) {
        $menuObjeto = Menu::where("external_id", $external_id)->first();
        if ($menuObjeto) {
            if ($request->isJson()) {
                $data = $request->json()->all();
                $menu = Menu::find($menuObjeto->id);
                if (isset($data["tipo"]))
                    $menu->tipo = $data["tipo"];
                if (isset($data["precio"]))
                    $menu->precio = $data["precio"];
                if (isset($data["descripcion"]))
                    $menu->descripcion = $data["descripcion"];

                $menu->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            }else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

    /**
     * Funcion para eliminar de manera logica, un perfil de menu
     * @param Request $request
     * @param type $external_id external del menu a eliminar
     * @return type
     */
    public function eliminar(Request $request, $external_id) {
        $menuObjeto = Menu::where("external_id", $external_id)->first();
        if ($menuObjeto) {
            if ($request->isJson()) {

                $menu = Menu::find($menuObjeto->id);

                $menu->estado = false;
                $menu->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            } else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

    /**
     * Funcion para listar menus, de acuerdo al local que los registro
     * @param type $external_id del local
     * @return type
     */
    public function listarporLocal($external_id) {
        $local = Local::where("external_id", $external_id )->first();
        if ($local) {
            $lista = Menu:: where('id_local', $local->id )
            ->where('estado',1)->get();
            foreach ($lista as $item) {
                $data[] = ["tipo" => $item->tipo, "descripcion" => $item->descripcion,
                    "precio" => $item->precio, "local" => $local->nombre, "external_id"=>$item->external_id];
            }
            return response()->json($data, 200);
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

    /**
     * Funcion que Lista todos los menus 
     * @return type
     */
    public function Listar() {
        $value = Local::orderBy('nombre')->first();
        $lista = Menu::orderBy('tipo')->get();
        $data = array();

        foreach ($lista as $item) {
            $value = Local::where('id', $item->id_local)->first();
            $data[] = ["tipo" => $item->tipo, "descripcion" => $item->descripcion,
                "precio" => $item->precio, "nombre" => $value->nombre];
        }
        return $data;
    }
    
    /**
     * Funcion para devolver todos los datos de un menu seleccionado
     * @param type $external_id del menu a listar
     * @return type
     */
    public function ver($external_id) {
        $lista = Menu::where("external_id", $external_id)->get();
        $data = array();
        foreach ($lista as $value) {

            $data[] = ["tipo" => $value->tipo, "descripcion" => $value->descripcion, "precio" => $value->precio];
        }
        return $data;
    }

}
