<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Menu;
use App\Models\Local;
use Illuminate\Http\Request;

/**
 * Description of MenuController
 *
 * @author AlejandroC
 */
class MenuController extends Controller {

    private $external_id;

    function __construct() {
        $this->middleware('auth', ['only' =>
            [
                'modificar', 'registrar', 'eliminar', 'listarporLocal'
            ]
        ]);
    }

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
                    $menu->local()->associate($local);

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

    public function listarporLocal($external_id) {
        $local = Local::where("external_id", $external_id)->first();
        if ($local) {
            $lista = Menu:: where('id_local', $local->id)->orderBy('tipo')->get();
            foreach ($lista as $item) {
                $data[] = ["tipo" => $item->tipo, "descripcion" => $item->descripcion,
                    "precio" => $item->precio, "local" => $local->nombre];
            }
            return response()->json($data, 200);
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

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

}
