package dev.demon.util;

import cc.funkemunky.api.tinyprotocol.api.Packet;
import dev.demon.base.event.PacketEvent;
import lombok.Getter;

@Getter
public class PacketUtil {

    public enum Packets {

        //Server packets
        SERVER_POSITION,
        SERVER_KEEPALIVE,
        SERVER_TRANSACTION,
        SERVER_VELOCITY,
        SERVER_RESPAWN,
        SERVER_OPEN_WINDOW,
        SERVER_ENTITY_METADATA,
        SERVER_ABILITES,
        SERVER_ITEM_SLOT,
        SERVER_ENTITY,
        SERVER_ENTITY_TELEPORT,
        SERVER_ENTITY_HEAD_ROTATION,
        SERVER_NAMED_ENTITY,
        SERVER_ENTITY_DESTORY,
        SERVER_REL_LOOK,
        SERVER_REL_POSITION,
        SERVER_REL_POSITION_LOOK,
        SERVER_PLAYER_SPAWN,
        SERVER_LIVING_SPAWN,
        SERVER_ATTACH,
        SERVER_DESTORY,
        SERVER_BLOCK_CHANGE,
        SERVER_EXPLODE,

        //Client packets
        CLIENT_TRANSACTION,
        CLIENT_KEEPALIVE,
        CLIENT_POSITION,
        CLIENT_FLYING,
        CLIENT_POSITION_LOOK,
        CLIENT_LOOK,
        CLIENT_BLOCK_PLACE,
        CLIENT_BLOCK_DIG,
        CLIENT_ARM_ANIMATION,
        CLIENT_USE_ENTITY,
        CLIENT_ENTITY_ACTION,
        CLIENT_COMMAND,
        CLIENT_CREATIVE_INVENTORY,
        CLIENT_CLOSE_WINDOW,
        CLIENT_HELD_ITEM_SLOT,
        CLIENT_CUSTOM_PAYLOAD,
        CLIENT_WINDOW_CLICK,
        CLIENT_TAB_COMPLETE,
        CLIENT_RESOUCE_PACK,
        CLIENT_SETTINGS,
        CLIENT_ABILITES,
        CLIENT_STEER,
        ENTITY_RIDE_MOVE,
        SERVER_BED,
        SERVER_REL_ENTITY_MOVE,
        SERVER_REL_TELEPORT,
        SERVER_EFFECT,
        SERVER_EFFECT_REMOVE,
        SERVER_MAP_CHUNK,
        SERVER_MULTI_BLOCK_CHANGE,
        //Other
        NOT_DEFINDED
    }

    public static Packets toPacket(PacketEvent event) {
        switch (event.getPacketType()) {

            //Server translation
            case Packet.Server.POSITION: {
                return Packets.SERVER_POSITION;
            }
            case Packet.Server.TRANSACTION: {
                return Packets.SERVER_TRANSACTION;
            }
            case Packet.Server.KEEP_ALIVE: {
                return Packets.SERVER_KEEPALIVE;
            }
            case Packet.Server.ENTITY_VELOCITY: {
                return Packets.SERVER_VELOCITY;
            }
            case Packet.Server.RESPAWN: {
                return Packets.SERVER_RESPAWN;
            }
            case Packet.Server.OPEN_WINDOW: {
                return Packets.SERVER_OPEN_WINDOW;
            }
            case Packet.Server.ENTITY_METADATA: {
                return Packets.SERVER_ENTITY_METADATA;
            }
            case Packet.Server.ABILITIES: {
                return Packets.SERVER_ABILITES;
            }
            case Packet.Server.HELD_ITEM: {
                return Packets.SERVER_ITEM_SLOT;
            }
            case Packet.Server.ENTITY: {
                return Packets.SERVER_ENTITY;
            }
            case Packet.Server.ENTITY_TELEPORT: {
                return Packets.SERVER_ENTITY_TELEPORT;
            }
            case Packet.Server.ENTITY_HEAD_ROTATION: {
                return Packets.SERVER_ENTITY_HEAD_ROTATION;
            }
            case Packet.Server.NAMED_ENTITY_SPAWN: {
                return Packets.SERVER_NAMED_ENTITY;
            }
            case Packet.Server.ENTITY_DESTROY: {
                return Packets.SERVER_ENTITY_DESTORY;
            }
            case Packet.Server.SPAWN_ENTITY: {
                return Packets.SERVER_LIVING_SPAWN;
            }
            case Packet.Server.REL_LOOK: {
                return Packets.SERVER_REL_LOOK;
            }
            case Packet.Server.REL_POSITION: {
                return Packets.SERVER_REL_POSITION;
            }
            case Packet.Server.REL_POSITION_LOOK: {
                return Packets.SERVER_REL_POSITION_LOOK;
            }
            case Packet.Server.ENTITY_EFFECT: {
                return Packets.SERVER_EFFECT;
            }

            case Packet.Server.ATTACH: {
                return Packets.SERVER_ATTACH;
            }

            case Packet.Server.MAP_CHUNK: {
                return Packets.SERVER_MAP_CHUNK;
            }

            case Packet.Server.BLOCK_CHANGE: {
                return Packets.SERVER_BLOCK_CHANGE;
            }

            case Packet.Server.EXPLOSION: {
                return Packets.SERVER_EXPLODE;
            }

            //Client translation
            case Packet.Client.KEEP_ALIVE: {
                return Packets.CLIENT_KEEPALIVE;
            }
            case Packet.Client.TRANSACTION: {
                return Packets.CLIENT_TRANSACTION;
            }
            case Packet.Client.POSITION: {
                return Packets.CLIENT_POSITION;
            }
            case Packet.Client.POSITION_LOOK: {
                return Packets.CLIENT_POSITION_LOOK;
            }
            case Packet.Client.FLYING: {
                return Packets.CLIENT_FLYING;
            }
            case Packet.Client.LOOK: {
                return Packets.CLIENT_LOOK;
            }
            case Packet.Client.BLOCK_PLACE: {
                return Packets.CLIENT_BLOCK_PLACE;
            }
            case Packet.Client.BLOCK_DIG: {
                return Packets.CLIENT_BLOCK_DIG;
            }
            case Packet.Client.ARM_ANIMATION: {
                return Packets.CLIENT_ARM_ANIMATION;
            }
            case Packet.Client.USE_ENTITY: {
                return Packets.CLIENT_USE_ENTITY;
            }
            case Packet.Client.ENTITY_ACTION: {
                return Packets.CLIENT_ENTITY_ACTION;
            }
            case Packet.Client.CLIENT_COMMAND: {
                return Packets.CLIENT_COMMAND;
            }
            case Packet.Client.CLOSE_WINDOW: {
                return Packets.CLIENT_CLOSE_WINDOW;
            }
            case Packet.Client.HELD_ITEM_SLOT: {
                return Packets.CLIENT_HELD_ITEM_SLOT;
            }
            case Packet.Client.CUSTOM_PAYLOAD: {
                return Packets.CLIENT_CUSTOM_PAYLOAD;
            }
            case Packet.Client.WINDOW_CLICK: {
                return Packets.CLIENT_WINDOW_CLICK;
            }
            case Packet.Client.TAB_COMPLETE: {
                return Packets.CLIENT_TAB_COMPLETE;
            }
            case Packet.Client.SETTINGS: {
                return Packets.CLIENT_SETTINGS;
            }
            case Packet.Client.ABILITIES: {
                return Packets.CLIENT_ABILITES;
            }
            case Packet.Client.STEER_VEHICLE: {
                return Packets.CLIENT_STEER;
            }
        }

        return Packets.NOT_DEFINDED;
    }
}

