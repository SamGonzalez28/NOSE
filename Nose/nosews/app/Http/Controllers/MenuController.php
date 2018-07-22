<?php

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

namespace App\Http\Controllers;

use App\Models\Menu;
use Illuminate\Http\Request;

/**
 * Description of MenuController
 *
 * @author alejo
 */
class MenuController extends Controller {

    private $external_id;

    public function listar() {
        $lista = \App\Models\Menu::where('id_local', $local)->get();
        $data = array();
        foreach ($lista as $item) {
            $data[] = ["tipo" => $item->tipo, "precio" => $item->precio,"descripcion" => $item->descripcion];
        }
//return response()->json($data,200);
        return $data;
    }

    public function registrar(Request $request) {
        if ($request->isJson()) {
            $data = $request->json()->all();
            try {
                $local = \App\Models\Local::where("external_id", $data["clave"])->first();
                if ($local) {
                    $menu = new Menu();
                    $menu->tipo = $data["tipo"];
                    $menu->precio = $data["precio"];
                    $menu->descripcion = $data["descripcion"];
                    $menu->external_id = utilidades\UUID::v4();
                    $menu->local()->associate($local);

                    $menu->save();
                }

                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
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
                $menu = Menu::find($menuObjeto->id_menu);
                if (isset($data["tipo"]))
                    $menu->nombre = $data["tipo"];
                if (isset($data["precio"]))
                    $menu->nombre = $data["precio"];
                if (isset($data["describcion"]))
                    $menu->nombre = $data["descripcion"];

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
                $data = $request->json()->all();
                $menu = Menu::find($menuObjeto->id_menu);
                if (isset($data["estado"]))
                    $menu->estado = $data["estado"];
                $menu->save();
                return response()->json(["mensaje" => "Operacion exitosa", "siglas" => "OE"], 200);
            }else {
                return response()->json(["mensaje" => "La data no tiene el formato deseado", "siglas" => "DNF"], 400);
            }
        } else {
            return response()->json(["mensaje" => "No se encontro ningun dato", "siglas" => "NDE"], 204);
        }
    }

}
